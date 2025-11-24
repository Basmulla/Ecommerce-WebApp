package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Processor")
@PrimaryKeyJoinColumn(name = "StaffID")
public class Processor extends Staff {

    @Column(name = "ProcessingStation")
    private String processingStation;

    @Column(name = "ShiftHours")
    private String shiftHours;
}
