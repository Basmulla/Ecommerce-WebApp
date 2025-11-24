package com.ecommerce.service;

import com.ecommerce.entity.Customer;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedCustomer.getName());
                    existing.setEmail(updatedCustomer.getEmail());
                    existing.setPhone(updatedCustomer.getPhone());
                    existing.setAddress(updatedCustomer.getAddress());
                    return customerRepository.save(existing);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
