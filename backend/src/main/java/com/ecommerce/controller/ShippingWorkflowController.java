package com.ecommerce.controller;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Shipping;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.ShippingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/shipping-workflow")
@RequiredArgsConstructor
public class ShippingWorkflowController {

    private final ShippingService shippingService;
    private final OrdersService ordersService;

    @PostMapping("/ship")
    public Shipping shipOrder(@RequestBody ShippingRequest req) {

        Orders order = ordersService.getById(req.getOrderId());
        if (order == null) throw new RuntimeException("Order not found");

        Shipping s = new Shipping();
        s.setShippingId(null);
        s.setOrder(order);
        s.setCourier(req.getCourier());
        s.setTrackingNum(req.getTracking());
        s.setDeliveryDate(req.getDeliveryDate());
        s.setStatus("SHIPPED");

        Shipping saved = shippingService.create(s);

        order.setStatus("SHIPPED");
        ordersService.update(order.getOrderId(), order);

        return saved;
    }

    @Data
    public static class ShippingRequest {
        private Long orderId;
        private String courier;
        private String tracking;
        private Date deliveryDate;
    }
}
