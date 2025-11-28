package com.ecommerce.service;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.entity.Payment;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repo;

    public Payment processPayment(PaymentRequest req) {

        Payment p = new Payment();
        p.setOrderId(req.getOrderId());
        p.setMethod(req.getMethod());
        p.setAmount(req.getAmount());
        p.setStaffId(req.getStaffId());

        return repo.save(p);
    }

    public Payment getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Payment> getByOrder(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public Payment update(Long id, Payment updated) {
        Payment p = getById(id);
        if (p == null) return null;

        p.setMethod(updated.getMethod());
        p.setAmount(updated.getAmount());
        p.setStaffId(updated.getStaffId());

        return repo.save(p);
    }

    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}
