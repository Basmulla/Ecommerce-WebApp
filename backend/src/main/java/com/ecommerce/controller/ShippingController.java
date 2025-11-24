package com.ecommerce.controller;

import com.ecommerce.entity.Shipping;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping
    public List<Shipping> getAll() {
        return shippingService.getAll();
    }

    @GetMapping("/{id}")
    public Shipping getById(@PathVariable Long id) {
        return shippingService.getById(id);
    }

    @PostMapping
    public Shipping create(@RequestBody Shipping s) {
        return shippingService.create(s);
    }

    @PutMapping("/{id}")
    public Shipping update(@PathVariable Long id, @RequestBody Shipping s) {
        return shippingService.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shippingService.delete(id);
    }
}
