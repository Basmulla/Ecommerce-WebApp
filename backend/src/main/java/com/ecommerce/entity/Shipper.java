package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHIPPERS")
public class Shipper extends Staff {

    public Shipper() {
        this.setRole("SHIPPER");
    }
}
