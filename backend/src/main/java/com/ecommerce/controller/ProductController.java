package com.ecommerce.controller;

import com.ecommerce.entity.Books;
import com.ecommerce.entity.Clothing;
import com.ecommerce.entity.Electronics;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    // ============================================================
    // BASE CRUD
    // ============================================================

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/update/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product updated) {
        return productService.update(id, updated);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return productService.delete(id);
    }


    // ============================================================
    // PRODUCT SEARCH / FILTER
    // ============================================================

    @GetMapping("/search")
    public List<Product> search(@RequestParam("keyword") String keyword) {
        return productService.searchByName(keyword);
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        return productService.findByBrand(brand);
    }

    @GetMapping("/price-range")
    public List<Product> priceRange(
            @RequestParam double min,
            @RequestParam double max
    ) {
        return productService.findByPriceRange(min, max);
    }

    @GetMapping("/active")
    public List<Product> activeProducts() {
        return productService.getActive();
    }


    // ============================================================
    // CATEGORY-SPECIFIC ENDPOINTS
    // ============================================================

    // -------- BOOKS --------
    @PostMapping("/books/create")
    public Books createBook(@RequestBody Books book) {
        return productService.createBook(book);
    }

    @GetMapping("/books/{id}")
    public Books getBook(@PathVariable Long id) {
        return productService.getBook(id);
    }

    // -------- CLOTHING --------
    @PostMapping("/clothing/create")
    public Clothing createClothing(@RequestBody Clothing clothing) {
        return productService.createClothing(clothing);
    }

    @GetMapping("/clothing/{id}")
    public Clothing getClothing(@PathVariable Long id) {
        return productService.getClothing(id);
    }

    // -------- ELECTRONICS --------
    @PostMapping("/electronics/create")
    public Electronics createElectronics(@RequestBody Electronics electronics) {
        return productService.createElectronics(electronics);
    }

    @GetMapping("/electronics/{id}")
    public Electronics getElectronics(@PathVariable Long id) {
        return productService.getElectronics(id);
    }


    // ============================================================
    // STAFF FILTER (used in frontend)
    // ============================================================

    @GetMapping("/staff/{staffId}")
    public List<Product> getProductsByStaff(@PathVariable Long staffId) {
        return productService.getProductsByStaff(staffId);
    }

}
