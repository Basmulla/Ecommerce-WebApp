package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @Column(name = "PRODUCTID")
    private Long productId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "STOCKQUANTITY")
    private Integer stockQuantity;

    @Column(name = "ISACTIVE")
    private String isActive;

    @ManyToOne
    @JoinColumn(name = "STAFFID")
    private Staff staff;

    // Getters + setters
}
