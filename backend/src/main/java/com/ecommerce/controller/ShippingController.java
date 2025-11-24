package com.ecommerce.controller;

import com.ecommerce.entity.Shipping;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService service;

    @GetMapping
    public List<Shipping> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Shipping getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Shipping create(@RequestBody Shipping s) {
        return service.create(s);
    }

    @PutMapping("/{id}")
    public Shipping update(@PathVariable Long id, @RequestBody Shipping updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
