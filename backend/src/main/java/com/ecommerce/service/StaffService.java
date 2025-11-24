package com.ecommerce.service;

import com.ecommerce.entity.Staff;
import com.ecommerce.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository repo;

    public List<Staff> getAll() {
        return repo.findAll();
    }

    public Staff getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Staff getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Staff create(Staff s) {
        s.setPassword(BCrypt.hashpw(s.getPassword(), BCrypt.gensalt()));
        return repo.save(s);
    }

    public Staff update(Long id, Staff updated) {
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
