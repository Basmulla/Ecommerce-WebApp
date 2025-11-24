package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @Column(name = "ProductID")
    private Long productId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private Double price;

    @Column(name = "Brand")
    private String brand;

    @Column(name = "StockQuantity")
    private Integer stockQuantity;

    @Column(name = "IsActive")
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "StaffID")
    private Staff staff;
}
