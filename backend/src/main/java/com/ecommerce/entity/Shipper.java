package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHIPPER")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Shipper extends Staff {

    @Column(name = "VEHICLEASSIGNED")
    private String vehicleAssigned;

    @Column(name = "SHIFTHOURS")
    private String shiftHours;

    // Getters & setters
}
