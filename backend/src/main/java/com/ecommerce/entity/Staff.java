package com.ecommerce.entity;

import javax.persistence.*;

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
    private String role;

    @Column(name = "EMAIL")
    private String email;

    // Getters + setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
