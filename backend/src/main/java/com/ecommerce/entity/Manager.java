package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "MANAGERS")
public class Manager extends Staff {

    public Manager() {
        this.setRole("MANAGER");
    }
}
