package com.ecommerce.service;

import com.ecommerce.entity.Staff;
import com.ecommerce.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    public Optional<Staff> getById(Long id) {
        return staffRepository.findById(id);
    }

    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff update(Long id, Staff updated) {
        return staffRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            existing.setRole(updated.getRole());
            return staffRepository.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
