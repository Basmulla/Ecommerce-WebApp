package com.ecommerce.controller;

import com.ecommerce.entity.Shipping;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ShippingWorkflowController {

    private final ShippingService shippingService;

    // ============================================================
    // TRIGGER SHIPPING WORKFLOW
    // (Frontend calls: POST /api/shipping/ship?orderId=123&staffId=50)
    // ============================================================
    @PostMapping("/ship")
    public Shipping shipOrder(
            @RequestParam("orderId") Long orderId,
            @RequestParam("staffId") Long staffId
    ) {
        return shippingService.shipOrder(orderId, staffId);
    }
}
