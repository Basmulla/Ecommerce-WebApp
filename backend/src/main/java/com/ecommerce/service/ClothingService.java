package com.ecommerce.service;

import com.ecommerce.entity.Clothing;
import com.ecommerce.repository.ClothingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothingService {

    private final ClothingRepository repo;

    public List<Clothing> getAll() {
        return repo.findAll();
    }

    public Clothing getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Clothing create(Clothing clothing) {
        return repo.save(clothing);
    }

    public Clothing update(Long id, Clothing updated) {
        if (!repo.existsById(id)) return null;
        updated.setProductId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    /* Filters */
    public List<Clothing> findBySize(String size) {
        return repo.findBySizeLabel(size);
    }

    public List<Clothing> findByGender(String gender) {
        return repo.findByGenderCategory(gender);
    }
}
