package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductBrowsingController {

    private final ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search/name")
    public List<Product> searchByName(@RequestParam String q) {
        return service.searchByName(q);
    }

    @GetMapping("/by-brand")
    public List<Product> findByBrand(@RequestParam String brand) {
        return service.findByBrand(brand);
    }

    @GetMapping("/active")
    public List<Product> activeProducts() {
        return service.getActive();
    }
}
