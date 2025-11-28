package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.OrderDetails;
import com.ecommerce.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OrdersController {

    private final OrdersService ordersService;

    // ============================================================
    // 1. CREATE ORDER
    // ============================================================
    @PostMapping("/create")
    public Orders createOrder(@RequestBody Orders order) {
        return ordersService.create(order);
    }

    // ============================================================
    // 2. ADD ITEM TO ORDER
    // ============================================================
    @PostMapping("/add-item")
    public OrderDetails addItem(@RequestBody OrderDetails item) {
        return ordersService.addItem(item);
    }

    // ============================================================
    // 3. CALCULATE TOTAL
    // ============================================================
    @PutMapping("/calculate-total/{orderId}")
    public Orders calculateTotal(@PathVariable Long orderId) {
        return ordersService.calculateTotal(orderId);
    }

    // ============================================================
    // 4. UPDATE ORDER STATUS
    // ============================================================
    @PutMapping("/status/{orderId}")
    public Orders updateStatus(
            @PathVariable Long orderId,
            @RequestParam("status") String status
    ) {
        return ordersService.updateStatus(orderId, status);
    }

    // ============================================================
    // 5. GET FULL ORDER (Order + OrderDetails)
    // ============================================================
    @GetMapping("/full/{id}")
    public Orders getFullOrder(@PathVariable Long id) {
        return ordersService.getFullOrder(id);
    }

    // ============================================================
    // 6. GET ALL ORDERS
    // ============================================================
    @GetMapping("/all")
    public List<Orders> getAll() {
        return ordersService.getAll();
    }

    // ============================================================
    // 7. GET ORDERS BY CUSTOMER
    // ============================================================
    @GetMapping("/customer/{id}")
    public List<Orders> getByCustomer(@PathVariable Long id) {
        return ordersService.getOrdersByCustomer(id);
    }

    // ============================================================
    // 8. GET ORDERS BY STAFF
    // ============================================================
    @GetMapping("/staff/{id}")
    public List<Orders> getByStaff(@PathVariable Long id) {
        return ordersService.getOrdersByStaff(id);
    }
}
