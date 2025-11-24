package com.ecommerce.service;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository repo;

    public OrderDetailsService(OrderDetailsRepository repo) {
        this.repo = repo;
    }

    public List<OrderDetails> getAll() {
        return repo.findAll();
    }

    public Optional<OrderDetails> getById(Long id) {
        return repo.findById(id);
    }

    public OrderDetails create(OrderDetails detail) {
        return repo.save(detail);
    }

    public OrderDetails update(Long id, OrderDetails updated) {
        return repo.findById(id).map(existing -> {
            existing.setQuantity(updated.getQuantity());
            existing.setPurchasePrice(updated.getPurchasePrice());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
