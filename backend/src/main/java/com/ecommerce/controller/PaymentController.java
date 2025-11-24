package com.ecommerce.controllers;

import com.ecommerce.entity.Payment;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.PaymentService;
import com.ecommerce.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrdersService ordersService;

    // =====================================================
    // CREATE PAYMENT
    // =====================================================
    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {

        // Order must exist
        Orders order = ordersService.getOrderById(payment.getOrderID());
        if (order == null) return ResponseEntity.badRequest().body(null);

        // Ensure order total is calculated
        if (order.getOrderTotal() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Optional: ensure payment = order total
        if (payment.getAmount() != order.getOrderTotal()) {
            // if you want strict validation: reject
            // return ResponseEntity.badRequest().body(null);

            // Otherwise accept but override with correct total
            payment.setAmount(order.getOrderTotal());
        }

        Payment saved = paymentService.createPayment(payment);

        // Automatically update order status → PAID
        order.setStatus("PAID");
        ordersService.updateOrder(order);

        return ResponseEntity.ok(saved);
    }

    // =====================================================
    // GET PAYMENT BY ORDER
    // =====================================================
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrder(@PathVariable Long orderId) {

        Payment p = paymentService.getByOrder(orderId);
        if (p == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p);
    }

    // =====================================================
    // GET PAYMENT BY ID
    // =====================================================
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Payment p = paymentService.getPayment(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // =====================================================
    // UPDATE PAYMENT
    // =====================================================
    @PutMapping("/update/{id}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long id,
            @RequestBody Payment newData) {

        Payment updated = paymentService.updatePayment(id, newData);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    // =====================================================
    // DELETE PAYMENT (RARELY USED)
    // =====================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {

        boolean ok = paymentService.deletePayment(id);
        if (!ok) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Payment deleted successfully.");
    }
}
