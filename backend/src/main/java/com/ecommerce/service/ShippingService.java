package com.ecommerce.service;

import com.ecommerce.entity.Shipping;
import com.ecommerce.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository repo;

    public List<Shipping> getAll() {
        return repo.findAll();
    }

    public Shipping getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Shipping create(Shipping shipping) {
        return repo.save(shipping);
    }

    public Shipping update(Long id, Shipping updated) {
        if (!repo.existsById(id)) return null;
        updated.setShippingId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    /** Get shipping info related to an order */
    public Shipping getByOrderId(Long orderId) {
        return repo.findByOrder_OrderId(orderId);
    }
}
