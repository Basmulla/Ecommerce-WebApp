package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/browse")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductBrowsingController {

    private final ProductService productService;

    // ============================================================
    // 1. GET ALL PRODUCTS (Frontend: /api/browse/products)
    // ============================================================
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    // ============================================================
    // 2. GET PRODUCT BY ID
    // ============================================================
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    // ============================================================
    // 3. SEARCH PRODUCTS BY KEYWORD
    // (Frontend sends keyword=xxxx)
    // ============================================================
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
        return productService.searchByName(keyword);
    }
}
