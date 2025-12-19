package com.ecommerce.controller;

import com.ecommerce.entity.Shipping;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ShippingController {

    private final ShippingService shippingService;

    // ============================================================
    // CREATE SHIPPING RECORD
    // ============================================================
    @PostMapping("/create")
    public Shipping create(@RequestBody Shipping shipping) {
        return shippingService.create(shipping);
    }

    // ============================================================
    // GET SHIPPING INFO BY ORDER
    // ============================================================
    @GetMapping("/order/{orderId}")
    public Shipping getByOrder(@PathVariable Long orderId) {
        return shippingService.getByOrder(orderId);
    }

    // ============================================================
    // UPDATE SHIPPING STATUS
    // Example: PUT /api/shipping/status/12?status=SHIPPED
    // ============================================================
    @PutMapping("/status/{id}")
    public Shipping updateStatus(
            @PathVariable Long id,
            @RequestParam("status") String status
    ) {
        return shippingService.updateStatus(id, status);
    }

    // ============================================================
    // UPDATE TRACKING NUMBER
    // ============================================================
    @PutMapping("/tracking/{id}")
    public Shipping updateTrackingNumber(
            @PathVariable Long id,
            @RequestParam("trackingNum") String trackingNum
    ) {
        return shippingService.updateTrackingNumber(id, trackingNum);
    }

    // ============================================================
    // MARK ORDER AS DELIVERED
    // PUT /api/shipping/deliver/15?dateDelivered=2024-12-05
    // ============================================================
    @PutMapping("/deliver/{id}")
    public Shipping deliver(
            @PathVariable Long id,
            @RequestParam("dateDelivered") Date dateDelivered
    ) {
        return shippingService.markDelivered(id, dateDelivered);
    }

    // ============================================================
    // DELETE SHIPPING RECORD
    // ============================================================
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return shippingService.delete(id);
    }
}
