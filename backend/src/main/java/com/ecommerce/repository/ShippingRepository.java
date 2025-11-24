package com.ecommerce.repository;

import com.ecommerce.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    Shipping findByOrder_OrderId(Long orderId);
}
