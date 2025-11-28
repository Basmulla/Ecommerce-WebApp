package com.ecommerce.service;

import com.ecommerce.entity.Electronics;
import com.ecommerce.repository.ElectronicsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectronicsService {

    private final ElectronicsRepository repo;

    public Electronics create(Electronics e) {
        return repo.save(e);
    }

    public Electronics getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Electronics> getAll() {
        return repo.findAll();
    }

    public Electronics update(Long id, Electronics updated) {
        Electronics e = getById(id);
        if (e == null) return null;

        e.setName(updated.getName());
        e.setPrice(updated.getPrice());
        e.setBrand(updated.getBrand());
        e.setActive(updated.isActive());
        e.setSpecs(updated.getSpecs());

        return repo.save(e);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
