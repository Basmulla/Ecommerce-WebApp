package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "PROCESSORS")
public class Processor extends Staff {

    public Processor() {
        this.setRole("PROCESSOR");
    }
}
