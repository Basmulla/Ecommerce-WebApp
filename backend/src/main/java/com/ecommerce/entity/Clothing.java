package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clothing")
@PrimaryKeyJoinColumn(name = "ProductID")
public class Clothing extends Product {

    @Column(name = "SizeLabel")
    private String sizeLabel;

    @Column(name = "Material")
    private String material;

    @Column(name = "GenderCategory")
    private String genderCategory;
}
