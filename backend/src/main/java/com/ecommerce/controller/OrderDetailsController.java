package com.ecommerce.controller;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    // ============================================================
    // CREATE ORDER ITEM
    // ============================================================
    @PostMapping("/create")
    public OrderDetails create(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.create(orderDetails);
    }

    // ============================================================
    // GET ALL ITEMS FOR A GIVEN ORDER
    // ============================================================
    @GetMapping("/order/{orderId}")
    public List<OrderDetails> getByOrder(@PathVariable Long orderId) {
        return orderDetailsService.getByOrder(orderId);
    }

    // ============================================================
    // GET ORDER DETAIL BY ID
    // ============================================================
    @GetMapping("/{id}")
    public OrderDetails getById(@PathVariable Long id) {
        return orderDetailsService.getById(id);
    }

    // ============================================================
    // UPDATE ORDER ITEM
    // ============================================================
    @PutMapping("/update/{id}")
    public OrderDetails update(
            @PathVariable Long id,
            @RequestBody OrderDetails updated
    ) {
        return orderDetailsService.update(id, updated);
    }

    // ============================================================
    // DELETE ORDER ITEM
    // ============================================================
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return orderDetailsService.delete(id);
    }
}
