package com.ecommerce.service;

import com.ecommerce.entity.Manager;
import com.ecommerce.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository repo;

    public ManagerService(ManagerRepository repo) {
        this.repo = repo;
    }

    public List<Manager> getAll() {
        return repo.findAll();
    }

    public Optional<Manager> getById(Long id) {
        return repo.findById(id);
    }

    public Manager create(Manager manager) {
        return repo.save(manager);
    }

    public Manager update(Long id, Manager updated) {
        return repo.findById(id).map(existing -> {
            existing.setOfficeNumber(updated.getOfficeNumber());
            existing.setSalary(updated.getSalary());
            existing.setYearsExperience(updated.getYearsExperience());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
