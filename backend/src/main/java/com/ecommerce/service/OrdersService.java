package com.ecommerce.service;

import com.ecommerce.entity.OrderDetails;
import com.ecommerce.entity.Orders;
import com.ecommerce.repository.OrderDetailsRepository;
import com.ecommerce.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository repo;
    private final OrderDetailsRepository detailsRepo;

    public Orders create(Orders o) {
        o.setStatus("PENDING");
        o.setTotal(0.0);
        return repo.save(o);
    }

    public Orders getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Orders> getAll() {
        return repo.findAll();
    }

    public List<Orders> getByCustomer(Long customerId) {
        return repo.findByCustomerId(customerId);
    }

    public List<Orders> getByStaff(Long staffId) {
        return repo.findByStaffId(staffId);
    }

    public OrderDetails addItem(OrderDetails item) {
        return detailsRepo.save(item);
    }

    public Orders updateStatus(Long id, String status) {
        Orders o = getById(id);
        if (o == null) return null;

        o.setStatus(status);
        return repo.save(o);
    }

    public Orders calculateTotal(Long orderId) {
        Orders o = getById(orderId);
        if (o == null) return null;

        List<OrderDetails> items = detailsRepo.findByOrderId(orderId);

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        o.setTotal(total);
        return repo.save(o);
    }
}
