package com.ecommerce.repository;

import com.ecommerce.entity.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicsRepository extends JpaRepository<Electronics, Long> {}
