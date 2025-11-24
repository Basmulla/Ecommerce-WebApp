package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "MANAGER")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Manager extends Staff {

    @Column(name = "OFFICENUMBER")
    private Long officeNumber;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "YEARSEXPERIENCE")
    private Integer yearsExperience;

    // Getters & setters
}
