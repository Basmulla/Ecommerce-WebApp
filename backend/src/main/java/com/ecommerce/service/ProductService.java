package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

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
        if (!repo.existsById(id)) return null;
        updated.setProductId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    /* ---- Filtering ---- */

    public List<Product> getByBrand(String brand) {
        return repo.findByBrand(brand);
    }

    public List<Product> getActiveProducts() {
        return repo.findByIsActive("Y");
    }

    public List<Product> getInactiveProducts() {
        return repo.findByIsActive("N");
    }

    public List<Product> getProductsByStaff(Long staffId) {
        return repo.findByStaff_StaffId(staffId);
    }
}
