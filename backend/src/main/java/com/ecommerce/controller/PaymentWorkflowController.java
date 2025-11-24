package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Payment;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.PaymentService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/payment")
public class PaymentWorkflowController {

    private final PaymentService paymentService;
    private final OrdersService ordersService;

    public PaymentWorkflowController(PaymentService paymentService, OrdersService ordersService) {
        this.paymentService = paymentService;
        this.ordersService = ordersService;
    }

    // ==========================================================
    // POST /api/payment/pay
    // Create a payment and update order status
    // ==========================================================
    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest req) {

        // 1️⃣ Get the order
        Orders order = ordersService.getById(req.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found: " + req.getOrderId()));

        // 2️⃣ Prevent duplicate payments
        if ("PAID".equalsIgnoreCase(order.getStatus())) {
            throw new RuntimeException("Order is already paid.");
        }

        // 3️⃣ Create payment record
        Payment payment = new Payment();
        payment.setOrderID(order.getOrderID());
        payment.setPaymentMethod(req.getMethod());
        payment.setAmount(order.getOrderTotal());
        payment.setDatePaid(new Date());

        paymentService.create(payment);

        // 4️⃣ Update order status
        order.setStatus("PAID");
        ordersService.update(order.getOrderID(), order);

        // 5️⃣ Response
        PaymentResponse resp = new PaymentResponse();
        resp.setOrderId(order.getOrderID());
        resp.setAmount(order.getOrderTotal());
        resp.setStatus("PAID");

        return resp;
    }

    // ==========================================================
    // Request body (JSON)
    // ==========================================================
    @Data
    public static class PaymentRequest {
        private Long orderId;
        private String method; // "CREDIT", "DEBIT", "PAYPAL", etc.
    }

    // ==========================================================
    // Response
    // ==========================================================
    @Data
    public static class PaymentResponse {
        private Long orderId;
        private double amount;
        private String status;
    }
}
