package com.ecommerce.service;

import com.ecommerce.entity.Orders;
import com.ecommerce.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository repo;

    public OrdersService(OrdersRepository repo) {
        this.repo = repo;
    }

    public List<Orders> getAll() {
        return repo.findAll();
    }

    public Optional<Orders> getById(Long id) {
        return repo.findById(id);
    }

    public Orders create(Orders o) {
        return repo.save(o);
    }

    public Orders update(Long id, Orders updated) {
        return repo.findById(id).map(existing -> {
            existing.setStatus(updated.getStatus());
            existing.setOrderDate(updated.getOrderDate());
            existing.setOrderTotal(updated.getOrderTotal());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
