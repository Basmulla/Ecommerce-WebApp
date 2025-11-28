package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository repo;
    private final ManagerRepository managerRepo;
    private final ProcessorRepository processorRepo;
    private final ShipperRepository shipperRepo;

    public Staff create(Staff s) {
        return repo.save(s);
    }

    public Manager createManager(Manager m) {
        m.setRole("MANAGER");
        return managerRepo.save(m);
    }

    public Processor createProcessor(Processor p) {
        p.setRole("PROCESSOR");
        return processorRepo.save(p);
    }

    public Shipper createShipper(Shipper s) {
        s.setRole("SHIPPER");
        return shipperRepo.save(s);
    }

    public Staff getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Staff> getAll() {
        return repo.findAll();
    }

    public List<Staff> getByRole(String role) {
        return repo.findByRole(role);
    }

    public Staff update(Long id, Staff s) {
        Staff existing = getById(id);
        if (existing == null) return null;

        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        existing.setPassword(s.getPassword());

        return repo.save(existing);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
