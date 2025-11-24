package com.ecommerce.service;

import com.ecommerce.entity.Orders;
import com.ecommerce.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository repo;

    public List<Orders> getAll() {
        return repo.findAll();
    }

    public Orders getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Orders create(Orders o) {
        return repo.save(o);
    }

    public Orders update(Long id, Orders updated) {
        if (!repo.existsById(id)) return null;
        updated.setOrderId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
