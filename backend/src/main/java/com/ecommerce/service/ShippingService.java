package com.ecommerce.service;

import com.ecommerce.entity.Shipping;
import com.ecommerce.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository repo;

    public Shipping create(Shipping s) {
        return repo.save(s);
    }

    public Shipping createForOrder(Long orderId) {
        Shipping s = new Shipping();
        s.setOrderId(orderId);
        s.setStatus("PROCESSING");
        return repo.save(s);
    }

    public Shipping getByOrder(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public Shipping updateStatus(Long id, String status) {
        Shipping s = repo.findById(id).orElse(null);
        if (s == null) return null;

        s.setStatus(status);
        return repo.save(s);
    }

    public Shipping updateTrackingNumber(Long id, String trackingNum) {
        Shipping s = repo.findById(id).orElse(null);
        if (s == null) return null;

        s.setTrackingNumber(trackingNum);
        return repo.save(s);
    }

    public Shipping markDelivered(Long id, Date delivered) {
        Shipping s = repo.findById(id).orElse(null);
        if (s == null) return null;

        s.setStatus("DELIVERED");
        s.setDateDelivered(delivered);
        return repo.save(s);
    }

    public Shipping shipOrder(Long orderId, Long staffId) {
        Shipping s = getByOrder(orderId);
        if (s == null) s = createForOrder(orderId);

        s.setStatus("SHIPPED");
        return repo.save(s);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
