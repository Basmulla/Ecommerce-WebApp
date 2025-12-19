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

    public Payment create(Payment p) {
        return repo.save(p);
    }

    public Payment getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Payment> getByOrder(Long orderId) {
        return repo.findByOrderId(orderId);
    }

    public Payment update(Long id, Payment updated) {
        Payment p = repo.findById(id).orElse(null);
        if (p == null) return null;

        p.setAmount(updated.getAmount());
        p.setMethod(updated.getMethod());
        p.setStatus(updated.getStatus());

        return repo.save(p);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    public Payment processPayment(PaymentRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processPayment'");
    }
}
