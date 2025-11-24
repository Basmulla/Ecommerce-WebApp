package com.ecommerce.service;

import com.ecommerce.entity.Payment;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repo;

    public List<Payment> getAll() {
        return repo.findAll();
    }

    public Payment getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Payment create(Payment p) {
        return repo.save(p);
    }

    public Payment update(Long id, Payment updated) {
        if (!repo.existsById(id)) return null;
        updated.setPaymentId(id);
        return repo.save(updated);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
