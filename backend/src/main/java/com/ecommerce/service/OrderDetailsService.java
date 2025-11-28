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

    public OrderDetails create(OrderDetails d) {
        return repo.save(d);
    }

    public OrderDetails getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<OrderDetails> getByOrder(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public OrderDetails update(Long id, OrderDetails updated) {
        OrderDetails d = getById(id);
        if (d == null) return null;

        d.setProductId(updated.getProductId());
        d.setQuantity(updated.getQuantity());
        d.setPrice(updated.getPrice());

        return repo.save(d);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
