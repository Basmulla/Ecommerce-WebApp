package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "PROCESSOR")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Processor extends Staff {

    @Column(name = "PROCESSINGSTATION")
    private String processingStation;

    @Column(name = "SHIFTHOURS")
    private String shiftHours;

    // Getters & setters
}
