package com.ecommerce.repository;

import com.ecommerce.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {
}
