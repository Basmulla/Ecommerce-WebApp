package com.ecommerce.controller;

import com.ecommerce.entity.Payment;
import com.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;

    // ============================================================
    // CREATE PAYMENT
    // ============================================================
    @PostMapping("/create")
    public Payment create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }

    // ============================================================
    // GET PAYMENTS BY ORDER ID
    // ============================================================
    @GetMapping("/order/{orderId}")
    public List<Payment> getByOrder(@PathVariable Long orderId) {
        return paymentService.getByOrder(orderId);
    }

    // ============================================================
    // GET PAYMENT BY ID
    // ============================================================
    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    // ============================================================
    // UPDATE PAYMENT
    // ============================================================
    @PutMapping("/update/{id}")
    public Payment update(
            @PathVariable Long id,
            @RequestBody Payment updated
    ) {
        return paymentService.update(id, updated);
    }

    // ============================================================
    // DELETE PAYMENT
    // ============================================================
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return paymentService.delete(id);
    }
}
