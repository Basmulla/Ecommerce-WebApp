package com.ecommerce.service;

import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public Staff create(Staff staff) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public Manager createManager(Manager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createManager'");
    }

    public Processor createProcessor(Processor processor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProcessor'");
    }

    public Shipper createShipper(Shipper shipper) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createShipper'");
    }

    public List<Staff> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    public List<Staff> getByRole(String role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByRole'");
    }

    public Staff update(Long id, Staff updatedStaff) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
