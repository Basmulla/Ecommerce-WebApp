package com.ecommerce.service;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository repo;

    public List<OrderDetails> getAll() {
        return repo.findAll();
    }

    public OrderDetails getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public OrderDetails create(OrderDetails od) {
        return repo.save(od);
    }

    public OrderDetails update(Long id, OrderDetails updated) {
        if (!repo.existsById(id)) return null;
        updated.setOrderDetailId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
