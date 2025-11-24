package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService service;

    // -------------------------------------------------------------
    // GET ALL ORDERS
    // -------------------------------------------------------------
    @GetMapping
    public List<Orders> getAll() {
        return service.getAll();
    }

    // -------------------------------------------------------------
    // GET ORDER BY ID
    // -------------------------------------------------------------
    @GetMapping("/{id}")
    public Orders getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // -------------------------------------------------------------
    // CREATE ORDER
    // -------------------------------------------------------------
    @PostMapping
    public Orders create(@RequestBody Orders orders) {
        return service.create(orders);
    }

    // -------------------------------------------------------------
    // UPDATE ORDER
    // -------------------------------------------------------------
    @PutMapping("/{id}")
    public Orders update(@PathVariable Long id, @RequestBody Orders updated) {
        return service.update(id, updated);
    }

    // -------------------------------------------------------------
    // DELETE ORDER
    // -------------------------------------------------------------
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
