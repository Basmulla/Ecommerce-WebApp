package com.ecommerce.repository;

import com.ecommerce.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {

    List<Clothing> findBySizeLabel(String sizeLabel);

    List<Clothing> findByGenderCategory(String genderCategory);
}
