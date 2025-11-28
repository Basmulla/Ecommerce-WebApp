package com.ecommerce.repository;

import com.ecommerce.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByEmail(String email);
    List<Staff> findByRole(String role);
}
