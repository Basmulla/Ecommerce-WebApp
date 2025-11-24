package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROCESSOR")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Processor extends Staff {

    @Column(name = "PROCESSINGSTATION")
    private String processingStation;

    @Column(name = "SHIFTHOURS")
    private String shiftHours;
}
