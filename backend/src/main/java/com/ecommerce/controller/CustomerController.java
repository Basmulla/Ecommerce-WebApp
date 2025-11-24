package com.ecommerce.controllers;

import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ---------------------------------------------------------
    // CREATE CUSTOMER
    // ---------------------------------------------------------
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        return ResponseEntity.ok(saved);
    }

    // ---------------------------------------------------------
    // GET ALL CUSTOMERS
    // ---------------------------------------------------------
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> list = customerService.getAllCustomers();
        return ResponseEntity.ok(list);
    }

    // ---------------------------------------------------------
    // GET CUSTOMER BY ID
    // ---------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer c = customerService.getCustomerById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    // ---------------------------------------------------------
    // UPDATE CUSTOMER
    // ---------------------------------------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer newData
    ) {
        Customer updated = customerService.updateCustomer(id, newData);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // ---------------------------------------------------------
    // DELETE CUSTOMER
    // ---------------------------------------------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        boolean ok = customerService.deleteCustomer(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Customer deleted successfully.");
    }
}
