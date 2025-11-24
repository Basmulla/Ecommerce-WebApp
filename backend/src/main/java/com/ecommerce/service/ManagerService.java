package com.ecommerce.service;

import com.ecommerce.entity.Manager;
import com.ecommerce.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository repo;

    public List<Manager> getAll() {
        return repo.findAll();
    }

    public Manager getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Manager create(Manager manager) {
        return repo.save(manager);
    }

    public Manager update(Long id, Manager updated) {
        if (!repo.existsById(id)) return null;
        updated.setStaffId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
