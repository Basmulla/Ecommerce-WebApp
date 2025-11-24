package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shipper")
@PrimaryKeyJoinColumn(name = "StaffID")
public class Shipper extends Staff {

    @Column(name = "VehicleAssigned")
    private String vehicleAssigned;

    @Column(name = "ShiftHours")
    private String shiftHours;
}
