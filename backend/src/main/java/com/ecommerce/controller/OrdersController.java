package com.ecommerce.controllers;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffService staffService;

    // =====================================================
    // CREATE ORDER (EMPTY ORDER FIRST)
    // =====================================================
    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {

        // Ensure customer exists
        Customer c = customerService.getCustomerById(order.getCustomerID());
        if (c == null) return ResponseEntity.badRequest().body(null);

        order.setStatus("PENDING");
        Orders saved = ordersService.createOrder(order);
        return ResponseEntity.ok(saved);
    }

    // =====================================================
    // ADD ORDER DETAILS (ITEMS)
    // =====================================================
    @PostMapping("/add-item")
    public ResponseEntity<OrderDetails> addItem(@RequestBody OrderDetails details) {

        // Check: order exists?
        Orders o = ordersService.getOrderById(details.getOrderID());
        if (o == null) return ResponseEntity.badRequest().body(null);

        // Check: product exists?
        Product p = productService.getProductById(details.getProductID());
        if (p == null) return ResponseEntity.badRequest().body(null);

        // Check stock:
        if (p.getStockQuantity() < details.getQuantity()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Reduce stock
        p.setStockQuantity(p.getStockQuantity() - details.getQuantity());
        productService.updateProduct(p.getProductID(), p);

        // Set purchase price at moment of checkout
        details.setPurchasePrice(p.getPrice());
        OrderDetails saved = orderDetailsService.addDetails(details);

        return ResponseEntity.ok(saved);
    }

    // =====================================================
    // CALCULATE ORDER TOTAL
    // =====================================================
    @PutMapping("/calculate-total/{orderId}")
    public ResponseEntity<Orders> calculateTotal(@PathVariable Long orderId) {

        Orders order = ordersService.getOrderById(orderId);
        if (order == null) return ResponseEntity.notFound().build();

        double total = orderDetailsService.calculateOrderTotal(orderId);
        order.setOrderTotal(total);

        Orders updated = ordersService.updateOrder(order);
        return ResponseEntity.ok(updated);
    }

    // =====================================================
    // UPDATE ORDER STATUS
    // =====================================================
    @PutMapping("/status/{orderId}")
    public ResponseEntity<Orders> updateStatus(
        @PathVariable Long orderId,
        @RequestParam String status) {

        Orders o = ordersService.getOrderById(orderId);
        if (o == null) return ResponseEntity.notFound().build();

        o.setStatus(status);
        Orders updated = ordersService.updateOrder(o);
        return ResponseEntity.ok(updated);
    }

    // =====================================================
    // GET ORDER WITH DETAILS (FULL ORDER VIEW)
    // =====================================================
    @GetMapping("/full/{orderId}")
    public ResponseEntity<Object> getFullOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(ordersService.getFullOrder(orderId));
    }

    // =====================================================
    // GET ALL ORDERS
    // =====================================================
    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    // =====================================================
    // GET ORDERS BY CUSTOMER
    // =====================================================
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Orders>> getOrdersByCustomer(@PathVariable Long customerId) {

        Customer c = customerService.getCustomerById(customerId);
        if (c == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ordersService.getOrdersByCustomer(customerId));
    }

    // =====================================================
    // GET ORDERS BY STAFF (Processor or Shipper)
    // =====================================================
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Orders>> getOrdersByStaff(@PathVariable Long staffId) {

        Staff s = staffService.getStaffById(staffId);
        if (s == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(ordersService.getOrdersByStaff(staffId));
    }
}
