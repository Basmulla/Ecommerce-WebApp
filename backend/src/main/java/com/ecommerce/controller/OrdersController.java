package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<Orders> getAll() {
        return ordersService.getAll();
    }

    @GetMapping("/{id}")
    public Orders getById(@PathVariable Long id) {
        return ordersService.getById(id);
    }

    @PostMapping
    public Orders create(@RequestBody Orders o) {
        return ordersService.create(o);
    }

    @PutMapping("/{id}")
    public Orders update(@PathVariable Long id, @RequestBody Orders o) {
        return ordersService.update(id, o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ordersService.delete(id);
    }
}
