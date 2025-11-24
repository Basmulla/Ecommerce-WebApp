package com.ecommerce.repository;

import com.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByCustomer_CustomerId(Long customerId);

    List<Orders> findByStatus(String status);
}
