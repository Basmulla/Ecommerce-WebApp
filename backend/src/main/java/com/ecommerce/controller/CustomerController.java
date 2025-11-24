package com.ecommerce.controller;

import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // -------------------------------------------------------------
    // GET ALL
    // -------------------------------------------------------------
    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    // -------------------------------------------------------------
    // GET BY ID
    // -------------------------------------------------------------
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // -------------------------------------------------------------
    // CREATE CUSTOMER
    // -------------------------------------------------------------
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    // -------------------------------------------------------------
    // UPDATE CUSTOMER
    // -------------------------------------------------------------
    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer updated) {
        return service.update(id, updated);
    }

    // -------------------------------------------------------------
    // DELETE CUSTOMER
    // -------------------------------------------------------------
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
