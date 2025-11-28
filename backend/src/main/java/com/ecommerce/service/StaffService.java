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

    public Staff getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Staff getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
