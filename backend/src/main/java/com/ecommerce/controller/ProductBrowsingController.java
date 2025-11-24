package com.ecommerce.controller;

import com.ecommerce.entity.Books;
import com.ecommerce.entity.Clothing;
import com.ecommerce.entity.Electronics;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.BooksRepository;
import com.ecommerce.repository.ClothingRepository;
import com.ecommerce.repository.ElectronicsRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/browse")
public class ProductBrowsingController {

    private final ProductRepository productRepo;
    private final BooksRepository booksRepo;
    private final ClothingRepository clothingRepo;
    private final ElectronicsRepository electronicsRepo;

    public ProductBrowsingController(
            ProductRepository productRepo,
            BooksRepository booksRepo,
            ClothingRepository clothingRepo,
            ElectronicsRepository electronicsRepo) {
        this.productRepo = productRepo;
        this.booksRepo = booksRepo;
        this.clothingRepo = clothingRepo;
        this.electronicsRepo = electronicsRepo;
    }

    // ----------------------------
    // Get ALL products
    // ----------------------------
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .filter(p -> "Y".equalsIgnoreCase(p.getIsActive()))
                .collect(Collectors.toList());
    }

    // ----------------------------
    // Get single product
    // ----------------------------
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepo.findById(id).orElse(null);
    }

    // ----------------------------
    // Search products by keyword
    // ----------------------------
    @GetMapping("/search")
    public List<Product> search(@RequestParam("keyword") String keyword) {
        String lower = keyword.toLowerCase();

        return productRepo.findAll().stream()
                .filter(p ->
                        (p.getName() != null && p.getName().toLowerCase().contains(lower)) ||
                        (p.getDescription() != null && p.getDescription().toLowerCase().contains(lower)) ||
                        (p.getBrand() != null && p.getBrand().toLowerCase().contains(lower))
                )
                .collect(Collectors.toList());
    }

    // ----------------------------
    // Get Books
    // ----------------------------
    @GetMapping("/books")
    public List<Books> getBooks() {
        return booksRepo.findAll();
    }

    // ----------------------------
    // Get Clothing
    // ----------------------------
    @GetMapping("/clothing")
    public List<Clothing> getClothing() {
        return clothingRepo.findAll();
    }

    // ----------------------------
    // Get Electronics
    // ----------------------------
    @GetMapping("/electronics")
    public List<Electronics> getElectronics() {
        return electronicsRepo.findAll();
    }
}
