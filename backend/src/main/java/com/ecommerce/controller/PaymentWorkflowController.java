package com.ecommerce.controller;

import com.ecommerce.entity.Payment;
import com.ecommerce.service.PaymentService;
import com.ecommerce.service.OrdersService;
import com.ecommerce.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentWorkflowController {

    private final PaymentService paymentService;
    private final OrdersService ordersService;

    // ============================================================
    // PROCESS PAYMENT  (frontend: POST /api/payment/pay)
    // ============================================================
    @PostMapping("/pay")
    public Payment processPayment(@RequestBody PaymentRequest request) {

        // 1. Create the payment record
        Payment payment = paymentService.processPayment(request);

        // 2. Update the order status to PAID
        ordersService.updateStatus(request.getOrderId(), "PAID");

        // 3. Return the created Payment object
        return payment;
    }
}
