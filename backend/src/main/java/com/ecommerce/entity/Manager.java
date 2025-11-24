package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MANAGER")
@PrimaryKeyJoinColumn(name = "STAFFID")
public class Manager extends Staff {

    @Column(name = "OFFICENUMBER")
    private Integer officeNumber;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "YEARSEXPEREIENCE")
    private Integer yearsExperience;
}
