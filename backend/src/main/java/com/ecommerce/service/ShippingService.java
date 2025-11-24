package com.ecommerce.service;

import com.ecommerce.entity.Shipping;
import com.ecommerce.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {

    private final ShippingRepository repo;

    public ShippingService(ShippingRepository repo) {
        this.repo = repo;
    }

    public List<Shipping> getAll() {
        return repo.findAll();
    }

    public Optional<Shipping> getById(Long id) {
        return repo.findById(id);
    }

    public Shipping create(Shipping shipping) {
        return repo.save(shipping);
    }

    public Shipping update(Long id, Shipping updated) {
        return repo.findById(id).map(existing -> {
            existing.setCourier(updated.getCourier());
            existing.setTrackingNum(updated.getTrackingNum());
            existing.setDeliveryDate(updated.getDeliveryDate());
            existing.setStatus(updated.getStatus());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
