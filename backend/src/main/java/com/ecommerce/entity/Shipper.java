package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHIPPER")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Shipper extends Staff {

    @Column(name = "VEHICLEASSIGNED")
    private String vehicleAssigned;

    @Column(name = "SHIFTHOURS")
    private String shiftHours;
}
