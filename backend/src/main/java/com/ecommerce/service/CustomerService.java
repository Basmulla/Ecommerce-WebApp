package com.ecommerce.service;

import com.ecommerce.entity.Customer;
import com.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public Customer create(Customer c) {
        return repo.save(c);
    }

    public List<Customer> getAll() {
        return repo.findAll();
    }

    public Customer getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Customer update(Long id, Customer updated) {
        Customer c = getById(id);
        if (c == null) return null;

        c.setName(updated.getName());
        c.setEmail(updated.getEmail());
        c.setPassword(updated.getPassword());
        c.setPhone(updated.getPhone());

        return repo.save(c);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
