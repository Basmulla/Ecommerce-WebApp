package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Electronics")
@PrimaryKeyJoinColumn(name = "ProductID")
public class Electronics extends Product {

    @Column(name = "WarrantyPeriod")
    private String warrantyPeriod;

    @Column(name = "PowerRating")
    private String powerRating;
}
