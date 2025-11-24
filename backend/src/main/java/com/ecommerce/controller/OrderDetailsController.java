package com.ecommerce.controller;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    // ==========================================================
    // GET ALL ORDER DETAILS
    // ==========================================================
    @GetMapping
    public List<OrderDetails> getAll() {
        return orderDetailsService.getAll();
    }

    // ==========================================================
    // GET ORDER DETAIL BY ID
    // ==========================================================
    @GetMapping("/{id}")
    public OrderDetails getById(@PathVariable Long id) {
        return orderDetailsService.getById(id);
    }

    // ==========================================================
    // CREATE ORDER DETAIL
    // ==========================================================
    @PostMapping
    public OrderDetails create(@RequestBody OrderDetails d) {
        return orderDetailsService.create(d);
    }

    // ==========================================================
    // UPDATE ORDER DETAIL
    // ==========================================================
    @PutMapping("/{id}")
    public OrderDetails update(@PathVariable Long id, @RequestBody OrderDetails d) {
        return orderDetailsService.update(id, d);
    }

    // ==========================================================
    // DELETE ORDER DETAIL
    // ==========================================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderDetailsService.delete(id);
    }
}
