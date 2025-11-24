package com.ecommerce.controller;

import com.ecommerce.entity.Shipping;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping-workflow")
@RequiredArgsConstructor
public class ShippingWorkflowController {

    private final ShippingService shippingService;

    @PostMapping("/dispatch/{orderId}")
    public Shipping dispatch(@PathVariable Long orderId, @RequestBody Shipping s) {
        s.setOrderID(orderId);
        s.setStatus("DISPATCHED");
        return shippingService.create(s);
    }
}
