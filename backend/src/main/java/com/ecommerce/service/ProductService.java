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

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Product create(Product p) {
        return repo.save(p);
    }

    public Books createBook(Books b) {
        return booksRepo.save(b);
    }

    public Clothing createClothing(Clothing c) {
        return clothingRepo.save(c);
    }

    public Electronics createElectronics(Electronics e) {
        return electronicsRepo.save(e);
    }

    public Books getBook(Long id) {
        return booksRepo.findById(id).orElse(null);
    }

    public Clothing getClothing(Long id) {
        return clothingRepo.findById(id).orElse(null);
    }

    public Electronics getElectronics(Long id) {
        return electronicsRepo.findById(id).orElse(null);
    }

    // Dummy implementation since you have no staff-product relationship
    public List<Product> getProductsByStaff(Long staffId) {
        return repo.findAll();
    }

    public List<Product> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> findByBrand(String brand) {
        return repo.findByBrandIgnoreCase(brand);
    }

    public List<Product> findByPriceRange(double min, double max) {
        return repo.findByPriceBetween(min, max);
    }
}
