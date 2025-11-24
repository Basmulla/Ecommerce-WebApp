package com.ecommerce.controllers;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private ClothingService clothingService;

    @Autowired
    private ElectronicsService electronicsService;

    // ============================================================
    // BASE PRODUCT CRUD
    // ============================================================

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product p = productService.getProductById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product newData) {

        Product updated = productService.updateProduct(id, newData);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean ok = productService.deleteProduct(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Product deleted successfully.");
    }

    // ============================================================
    // PRODUCT SEARCH + FILTERING
    // ============================================================

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(productService.getByBrand(brand));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Product>> getActiveProducts() {
        return ResponseEntity.ok(productService.getActiveProducts());
    }

    // ============================================================
    // CATEGORY BROWSING (Michelle’s UI)
    // ============================================================

    @GetMapping("/category/books")
    public ResponseEntity<List<Books>> getBooks() {
        return ResponseEntity.ok(booksService.getAllBooks());
    }

    @GetMapping("/category/clothing")
    public ResponseEntity<List<Clothing>> getClothing() {
        return ResponseEntity.ok(clothingService.getAllClothing());
    }

    @GetMapping("/category/electronics")
    public ResponseEntity<List<Electronics>> getElectronics() {
        return ResponseEntity.ok(electronicsService.getAllElectronics());
    }

    // ============================================================
    // SUBTYPE CRUD
    // ============================================================

    // BOOKS
    @PostMapping("/books/create")
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        return ResponseEntity.ok(booksService.createBook(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Books b = booksService.getBook(id);
        if (b == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(b);
    }

    // CLOTHING
    @PostMapping("/clothing/create")
    public ResponseEntity<Clothing> createClothing(@RequestBody Clothing clothing) {
        return ResponseEntity.ok(clothingService.createClothing(clothing));
    }

    @GetMapping("/clothing/{id}")
    public ResponseEntity<Clothing> getClothingById(@PathVariable Long id) {
        Clothing c = clothingService.getClothing(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    // ELECTRONICS
    @PostMapping("/electronics/create")
    public ResponseEntity<Electronics> createElectronics(@RequestBody Electronics electronics) {
        return ResponseEntity.ok(electronicsService.createElectronics(electronics));
    }

    @GetMapping("/electronics/{id}")
    public ResponseEntity<Electronics> getElectronicsById(@PathVariable Long id) {
        Electronics e = electronicsService.getElectronics(id);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    // ============================================================
    // STAFF → PRODUCT LOOKUP
    // ============================================================

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Product>> getProductsByStaff(@PathVariable Long staffId) {
        return ResponseEntity.ok(productService.getProductsByStaff(staffId));
    }
}
