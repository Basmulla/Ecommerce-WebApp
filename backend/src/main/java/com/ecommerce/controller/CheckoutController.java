package com.ecommerce.controller;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Orders;
import com.ecommerce.entity.Product;
import com.ecommerce.service.OrderDetailsService;
import com.ecommerce.service.OrdersService;
import com.ecommerce.service.ProductService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final OrdersService ordersService;
    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;

    public CheckoutController(
            OrdersService ordersService,
            OrderDetailsService orderDetailsService,
            ProductService productService) {

        this.ordersService = ordersService;
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
    }

    // ==========================================================
    // POST /api/checkout
    // This receives the full cart and performs checkout
    // ==========================================================
    @PostMapping
    public CheckoutResponse checkout(@RequestBody CheckoutRequest req) {

        // 1️⃣ Create base order
        Orders order = new Orders();
        order.setCustomerID(req.getCustomerId());
        order.setOrderDate(new Date());
        order.setStatus("PENDING"); // initial status
        order.setOrderTotal(0.0);   // will update after items

        Orders savedOrder = ordersService.create(order);

        double total = 0.0;

        // 2️⃣ Loop through all cart items
        for (CartItem item : req.getItems()) {

            Product product = productService.getById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            // Validate stock
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + product.getName());
            }

            double price = product.getPrice();
            double lineTotal = price * item.getQuantity();
            total += lineTotal;

            // 3️⃣ Create OrderDetails entry
            OrderDetails od = new OrderDetails();
            od.setOrderID(savedOrder.getOrderID());
            od.setProductID(product.getProductID());
            od.setQuantity(item.getQuantity());
            od.setPurchasePrice(price);

            orderDetailsService.create(od);

            // 4️⃣ Deduct stock
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productService.update(product.getProductID(), product);
        }

        // 5️⃣ Update order total
        savedOrder.setOrderTotal(total);
        savedOrder.setStatus("CREATED"); // after items added
        ordersService.update(savedOrder.getOrderID(), savedOrder);

        // 6️⃣ Return summary
        CheckoutResponse resp = new CheckoutResponse();
        resp.setOrderId(savedOrder.getOrderID());
        resp.setCustomerId(savedOrder.getCustomerID());
        resp.setTotal(total);
        resp.setStatus(savedOrder.getStatus());

        return resp;
    }

    // ==========================================================
    // Request Body Classes
    // ==========================================================
    @Data
    public static class CheckoutRequest {
        private Long customerId;
        private List<CartItem> items;
    }

    @Data
    public static class CartItem {
        private Long productId;
        private int quantity;
    }

    // ==========================================================
    // Response Class
    // ==========================================================
    @Data
    public static class CheckoutResponse {
        private Long orderId;
        private Long customerId;
        private double total;
        private String status;
    }
}
