package com.ecommerce.service;

import com.ecommerce.entity.Orders;
import com.ecommerce.entity.OrderDetails;
import com.ecommerce.repository.OrderDetailsRepository;
import com.ecommerce.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepo;
    private final OrderDetailsRepository detailsRepo;

    public Orders create(Orders order) {
        return ordersRepo.save(order);
    }

    public OrderDetails addItem(OrderDetails item) {
        return detailsRepo.save(item);
    }

    public Orders calculateTotal(Long orderId) {
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order == null) return null;

        double total = detailsRepo.findByOrderId(orderId)
                .stream()
                .mapToDouble(d -> {
                    Double price = d.getPrice() == null ? 0.0 : d.getPrice();
                    Integer qty = d.getQuantity() == null ? 0 : d.getQuantity();
                    return price * qty;
                })
                .sum();

        order.setTotal(total);
        return ordersRepo.save(order);
    }

    public Orders updateStatus(Long orderId, String status) {
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order == null) return null;

        order.setStatus(status);
        return ordersRepo.save(order);
    }

    public Orders getFullOrder(Long id) {
        return ordersRepo.findById(id).orElse(null);
    }

    public List<Orders> getAll() {
        return ordersRepo.findAll();
    }

    public List<Orders> getOrdersByCustomer(Long id) {
        return ordersRepo.findByCustomerId(id);
    }

    public List<Orders> getOrdersByStaff(Long id) {
        return ordersRepo.findByStaffId(id);
    }
}
