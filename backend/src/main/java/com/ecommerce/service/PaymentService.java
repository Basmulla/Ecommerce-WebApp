package com.ecommerce.service;

import com.ecommerce.entity.Payment;
import com.ecommerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public List<Payment> getAll() {
        return repo.findAll();
    }

    public Optional<Payment> getById(Long id) {
        return repo.findById(id);
    }

    public Payment create(Payment payment) {
        return repo.save(payment);
    }

    public Payment update(Long id, Payment updated) {
        return repo.findById(id).map(existing -> {
            existing.setPaymentMethod(updated.getPaymentMethod());
            existing.setAmount(updated.getAmount());
            existing.setDatePaid(updated.getDatePaid());
            return repo.save(existing);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
