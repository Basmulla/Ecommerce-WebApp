package com.ecommerce.controller;

import com.ecommerce.entity.*;
import com.ecommerce.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final OrdersService ordersService;
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;

    // ---------------------------------------------------------------
    // CHECKOUT
    // ---------------------------------------------------------------
    @PostMapping
    public CheckoutResponse checkout(@RequestBody CheckoutRequest req) {

        // 1) Create ORDER
        Orders order = new Orders();
        order.setOrderId(null);  // auto-assigned by DB sequence
        order.setOrderDate(new Date());
        order.setStatus("PENDING");

        // Customer reference
        Customer c = new Customer();
        c.setCustomerId(req.getCustomerId());
        order.setCustomer(c);

        // Save order
        Orders savedOrder = ordersService.create(order);

        double total = 0;

        // 2) Process cart items
        for (CartItem item : req.getItems()) {

            Product product = productService.getById(item.getProductId());
            if (product == null) continue;

            // Validate stock
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // OrderDetails
            OrderDetails od = new OrderDetails();
            od.setOrderDetailId(null); // DB sequence
            od.setOrder(savedOrder);
            od.setProduct(product);
            od.setQuantity(item.getQuantity());
            od.setPurchasePrice(product.getPrice());

            orderDetailsService.create(od);

            // Update stock
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productService.update(product.getProductId(), product);

            // Accumulate total
            total += product.getPrice() * item.getQuantity();
        }

        // 3) Update order totals
        savedOrder.setOrderTotal(total);
        savedOrder.setStatus("PAID");
        ordersService.update(savedOrder.getOrderId(), savedOrder);

        // 4) Response
        CheckoutResponse resp = new CheckoutResponse();
        resp.setOrderId(savedOrder.getOrderId());
        resp.setCustomerId(savedOrder.getCustomer().getCustomerId());
        resp.setTotal(total);
        resp.setStatus(savedOrder.getStatus());

        return resp;
    }


    // ===============================================================
    // DTO CLASSES
    // ===============================================================

    @Data
    public static class CheckoutRequest {
        private Long customerId;
        private List<CartItem> items;
    }

    @Data
    public static class CartItem {
        private Long productId;
        private Integer quantity;
    }

    @Data
    public static class CheckoutResponse {
        private Long orderId;
        private Long customerId;
        private double total;
        private String status;
    }
}
