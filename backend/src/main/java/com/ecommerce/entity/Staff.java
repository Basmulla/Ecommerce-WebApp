package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STAFF")
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff {

    @Id
    @Column(name = "STAFFID")
    private Long staffId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ROLE", nullable = false)
    private String role; // Manager / Processor / Shipper

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
	
