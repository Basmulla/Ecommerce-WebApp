package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Manager")
@PrimaryKeyJoinColumn(name = "StaffId")
public class Manager extends Staff {

    @Column(name ="OfficeNumber")
    private Integer officeNumber;

    @Column(name = "Salary")
    private Double salary;

    @Column(name = "YearsExpereience")
    private Integer yearsExperience;
}
