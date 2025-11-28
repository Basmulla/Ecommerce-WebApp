package com.ecommerce.controller;

import com.ecommerce.dto.CheckoutRequest;
import com.ecommerce.dto.CheckoutResponse;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Shipping;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.OrderDetailsService;
import com.ecommerce.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CheckoutController {

    private final OrdersService ordersService;
    private final OrderDetailsService orderDetailsService;
    private final ShippingService shippingService;

    // ============================================================
    // CHECKOUT (FRONTEND: POST /api/checkout)
    // ============================================================
    @PostMapping
    public CheckoutResponse checkout(@RequestBody CheckoutRequest req) {

        // 1. Create base order
        Orders order = new Orders();
        order.setCustomerId(req.getCustomerId());
        order.setStaffId(req.getStaffId());
        order.setStatus("PENDING");
        Orders savedOrder = ordersService.create(order);

        // 2. Create all order details
        List<OrderDetails> savedItems = new ArrayList<>();
        req.getCartItems().forEach(item -> {
            OrderDetails detail = new OrderDetails();
            detail.setOrderId(savedOrder.getOrderId());
            detail.setProductId(item.getProductId());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getPrice());

            savedItems.add(orderDetailsService.create(detail));
        });

        // 3. Calculate the order total
        Orders updatedOrder = ordersService.calculateTotal(savedOrder.getOrderId());

        // 4. Create shipping record
        Shipping shipping = shippingService.createForOrder(updatedOrder.getOrderId());

        // 5. Build and return the response
        return new CheckoutResponse(updatedOrder, savedItems, shipping);
    }
}
