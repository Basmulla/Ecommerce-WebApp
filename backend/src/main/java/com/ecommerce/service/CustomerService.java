package com.ecommerce.service;

import com.ecommerce.entity.Customer;
import com.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Customer getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Customer getByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Customer create(Customer c) {
        c.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
        return repo.save(c);
    }

    public Customer update(Long id, Customer updated) {
        if (!repo.existsById(id)) return null;
        updated.setCustomerId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
