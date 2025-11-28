package com.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "ELECTRONICS")
public class Electronics extends Product {

    private String specs;

    public Electronics() {}

    public String getSpecs() {
        return specs;
    }
    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
