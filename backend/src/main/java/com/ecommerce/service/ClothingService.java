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

    public Clothing create(Clothing c) {
        return repo.save(c);
    }

    public Clothing getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Clothing> getAll() {
        return repo.findAll();
    }

    public List<Clothing> getBySize(String size) {
        return repo.findBySize(size);
    }

    public List<Clothing> getByColor(String color) {
        return repo.findByColorIgnoreCase(color);
    }

    public Clothing update(Long id, Clothing updated) {
        Clothing c = getById(id);
        if (c == null) return null;

        c.setName(updated.getName());
        c.setPrice(updated.getPrice());
        c.setBrand(updated.getBrand());
        c.setActive(updated.isActive());
        c.setSize(updated.getSize());
        c.setColor(updated.getColor());

        return repo.save(c);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
