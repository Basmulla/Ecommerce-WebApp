package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final BooksRepository booksRepo;
    private final ElectronicsRepository electronicsRepo;
    private final ClothingRepository clothingRepo;

    // BASE CRUD
    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Product update(Long id, Product updated) {
        Product existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setBrand(updated.getBrand());
        existing.setPrice(updated.getPrice());
        existing.setActive(updated.isActive());
        existing.setCategory(updated.getCategory());

        return repo.save(existing);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }


    // SEARCH & FILTER
    public List<Product> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> findByBrand(String brand) {
        return repo.findByBrandIgnoreCase(brand);
    }

    public List<Product> findByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }

    public List<Product> getActive() {
        return repo.findByActiveTrue();
    }


    // CATEGORY SPECIFIC
    public Books createBook(Books b) {
        return booksRepo.save(b);
    }

    public Books getBook(Long id) {
        return booksRepo.findById(id).orElse(null);
    }

    public Clothing createClothing(Clothing c) {
        return clothingRepo.save(c);
    }

    public Clothing getClothing(Long id) {
        return clothingRepo.findById(id).orElse(null);
    }

    public Electronics createElectronics(Electronics e) {
        return electronicsRepo.save(e);
    }

    public Electronics getElectronics(Long id) {
        return electronicsRepo.findById(id).orElse(null);
    }


    // STAFF FILTER (dummy)
    public List<Product> getProductsByStaff(Long staffId) {
        // No relationship exists — return all products
        return repo.findAll();
    }
}
