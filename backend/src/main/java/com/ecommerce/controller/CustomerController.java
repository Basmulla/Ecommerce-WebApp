package com.ecommerce.controller;

import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    // ============================================================
    // CREATE CUSTOMER
    // ============================================================
    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    // ============================================================
    // GET ALL CUSTOMERS
    // ============================================================
    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    // ============================================================
    // GET CUSTOMER BY ID
    // ============================================================
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    // ============================================================
    // UPDATE CUSTOMER
    // ============================================================
    @PutMapping("/update/{id}")
    public Customer update(
            @PathVariable Long id,
            @RequestBody Customer updatedCustomer
    ) {
        return customerService.update(id, updatedCustomer);
    }

    // ============================================================
    // DELETE CUSTOMER
    // ============================================================
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
}
