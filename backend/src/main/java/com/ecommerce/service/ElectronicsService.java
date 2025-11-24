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

    public List<Electronics> getAll() {
        return repo.findAll();
    }

    public Electronics getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Electronics create(Electronics e) {
        return repo.save(e);
    }

    public Electronics update(Long id, Electronics updated) {
        if (!repo.existsById(id)) return null;
        updated.setProductId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
