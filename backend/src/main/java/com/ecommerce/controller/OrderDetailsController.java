package com.ecommerce.controller;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsService service;

    // -------------------------------------------------------------
    // GET ALL ORDER DETAILS
    // -------------------------------------------------------------
    @GetMapping
    public List<OrderDetails> getAll() {
        return service.getAll();
    }

    // -------------------------------------------------------------
    // GET ORDER DETAIL BY ID
    // -------------------------------------------------------------
    @GetMapping("/{id}")
    public OrderDetails getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // -------------------------------------------------------------
    // CREATE ORDER DETAIL
    // -------------------------------------------------------------
    @PostMapping
    public OrderDetails create(@RequestBody OrderDetails od) {
        return service.create(od);
    }

    // -------------------------------------------------------------
    // UPDATE ORDER DETAIL
    // -------------------------------------------------------------
    @PutMapping("/{id}")
    public OrderDetails update(@PathVariable Long id, @RequestBody OrderDetails updated) {
        return service.update(id, updated);
    }

    // -------------------------------------------------------------
    // DELETE ORDER DETAIL
    // -------------------------------------------------------------
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
