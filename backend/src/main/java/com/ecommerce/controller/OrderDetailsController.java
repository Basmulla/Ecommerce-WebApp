package com.ecommerce.controllers;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrderDetailsService;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin("*")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    // =====================================================
    // CREATE LINE ITEM
    // =====================================================
    @PostMapping("/create")
    public ResponseEntity<OrderDetails> createOrderDetail(@RequestBody OrderDetails details) {

        Orders order = ordersService.getOrderById(details.getOrderID());
        if (order == null) return ResponseEntity.badRequest().body(null);

        Product product = productService.getProductById(details.getProductID());
        if (product == null) return ResponseEntity.badRequest().body(null);

        // Ensure stock is available
        if (product.getStockQuantity() < details.getQuantity()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Reduce stock
        product.setStockQuantity(product.getStockQuantity() - details.getQuantity());
        productService.updateProduct(product.getProductID(), product);

        details.setPurchasePrice(product.getPrice());

        OrderDetails saved = orderDetailsService.addDetails(details);
        return ResponseEntity.ok(saved);
    }

    // =====================================================
    // GET ALL LINE ITEMS FOR AN ORDER
    // =====================================================
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> getItemsByOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailsService.getItemsByOrder(orderId));
    }

    // =====================================================
    // GET ONE LINE ITEM
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getDetail(@PathVariable Long id) {
        OrderDetails d = orderDetailsService.getDetail(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    // =====================================================
    // UPDATE LINE ITEM
    // =====================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDetails> updateDetail(
            @PathVariable Long id,
            @RequestBody OrderDetails newData) {

        OrderDetails updated = orderDetailsService.updateDetail(id, newData);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    // =====================================================
    // DELETE LINE ITEM
    // =====================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable Long id) {

        boolean ok = orderDetailsService.deleteDetail(id);
        if (!ok) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Order detail removed successfully.");
    }
}
