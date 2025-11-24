package com.ecommerce.controllers;

import com.ecommerce.entity.Shipping;
import com.ecommerce.entity.Orders;
import com.ecommerce.service.ShippingService;
import com.ecommerce.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
@CrossOrigin("*")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrdersService ordersService;

    // =====================================================================
    // CREATE SHIPPING RECORD
    // =====================================================================
    @PostMapping("/create")
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {

        Orders order = ordersService.getOrderById(shipping.getOrderID());
        if (order == null) return ResponseEntity.badRequest().body(null);

        // Auto set status when created
        shipping.setStatus("PREPARING");

        Shipping saved = shippingService.createShipping(shipping);

        // Update order status
        order.setStatus("PREPARING_SHIPMENT");
        ordersService.updateOrder(order);

        return ResponseEntity.ok(saved);
    }

    // =====================================================================
    // GET SHIPPING INFO BY ORDER
    // =====================================================================
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Shipping> getShippingByOrder(@PathVariable Long orderId) {

        Shipping s = shippingService.getByOrder(orderId);
        if (s == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(s);
    }

    // =====================================================================
    // UPDATE SHIPPING STATUS (Michelle’s UI)
    // =====================================================================
    @PutMapping("/status/{orderId}")
    public ResponseEntity<Shipping> updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    ) {
        Shipping shipping = shippingService.getByOrder(orderId);
        if (shipping == null) return ResponseEntity.notFound().build();

        shipping.setStatus(status);
        Shipping updated = shippingService.updateShipping(shipping.getShippingID(), shipping);

        // Auto-update order status too
        Orders order = ordersService.getOrderById(orderId);
        if (order != null) {
            if (status.equalsIgnoreCase("SHIPPED")) {
                order.setStatus("SHIPPED");
            } else if (status.equalsIgnoreCase("DELIVERED")) {
                order.setStatus("DELIVERED");
            } else {
                order.setStatus("IN_TRANSIT");
            }
            ordersService.updateOrder(order);
        }

        return ResponseEntity.ok(updated);
    }

    // =====================================================================
    // UPDATE TRACKING NUMBER
    // =====================================================================
    @PutMapping("/tracking/{orderId}")
    public ResponseEntity<Shipping> updateTracking(
            @PathVariable Long orderId,
            @RequestParam String trackingNum
    ) {
        Shipping shipping = shippingService.getByOrder(orderId);
        if (shipping == null) return ResponseEntity.notFound().build();

        shipping.setTrackingNum(trackingNum);
        Shipping updated = shippingService.updateShipping(shipping.getShippingID(), shipping);

        return ResponseEntity.ok(updated);
    }

    // =====================================================================
    // MARK AS DELIVERED (Final step)
    // =====================================================================
    @PutMapping("/deliver/{orderId}")
    public ResponseEntity<Shipping> markDelivered(
            @PathVariable Long orderId,
            @RequestParam String dateDelivered
    ) {
        Shipping shipping = shippingService.getByOrder(orderId);
        if (shipping == null) return ResponseEntity.notFound().build();

        shipping.setDeliveryDate(java.sql.Date.valueOf(dateDelivered));
        shipping.setStatus("DELIVERED");

        Shipping updated = shippingService.updateShipping(shipping.getShippingID(), shipping);

        // Update the order status
        Orders order = ordersService.getOrderById(orderId);
        if (order != null) {
            order.setStatus("DELIVERED");
            ordersService.updateOrder(order);
        }

        return ResponseEntity.ok(updated);
    }

    // =====================================================================
    // DELETE SHIPPING RECORD (Rarely used)
    // =====================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Long id) {

        boolean ok = shippingService.deleteShipping(id);
        if (!ok) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Shipping record deleted successfully.");
    }
}
