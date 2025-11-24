package com.ecommerce.repository;

import com.ecommerce.entity.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ElectronicsRepository extends JpaRepository<Electronics, Long> {

    List<Electronics> findByWarrantyPeriod(String warrantyPeriod);
}
