package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Shipping;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.ShippingService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/shipping")
public class ShippingWorkflowController {

    private final ShippingService shippingService;
    private final OrdersService ordersService;

    public ShippingWorkflowController(ShippingService shippingService, OrdersService ordersService) {
        this.shippingService = shippingService;
        this.ordersService = ordersService;
    }

    // ==========================================================
    // POST /api/shipping/ship
    // Create a shipping record and mark the order as SHIPPED
    // ==========================================================
    @PostMapping("/ship")
    public ShippingResponse createShipment(@RequestBody ShippingRequest req) {

        // 1️⃣ Fetch the order
        Orders order = ordersService.getById(req.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found: " + req.getOrderId()));

        // 2️⃣ Order must be paid
        if (!"PAID".equalsIgnoreCase(order.getStatus())) {
            throw new RuntimeException("Order must be PAID before shipping.");
        }

        // 3️⃣ Create shipping record
        Shipping shipping = new Shipping();
        shipping.setOrderID(order.getOrderID());
        shipping.setCourier(req.getCourier());
        shipping.setTrackingNum(req.getTrackingNum());
        shipping.setDeliveryDate(req.getDeliveryDate());
        shipping.setStatus("SHIPPED");

        shippingService.create(shipping);

        // 4️⃣ Update order state
        order.setStatus("SHIPPED");
        ordersService.update(order.getOrderID(), order);

        // 5️⃣ Response to frontend
        ShippingResponse resp = new ShippingResponse();
        resp.setOrderId(order.getOrderID());
        resp.setCourier(req.getCourier());
        resp.setTrackingNum(req.getTrackingNum());
        resp.setStatus("SHIPPED");

        return resp;
    }

    // ==========================================================
    // Request JSON
    // ==========================================================
    @Data
    public static class ShippingRequest {
        private Long orderId;
        private String courier;
        private String trackingNum;
        private Date deliveryDate;
    }

    // ==========================================================
    // Response JSON
    // ==========================================================
    @Data
    public static class ShippingResponse {
        private Long orderId;
        private String courier;
        private String trackingNum;
        private String status;
    }
}
