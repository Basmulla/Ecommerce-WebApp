package com.ecommerce.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "SHIPPING")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shippingId;

    private Long orderId;
    private String status;
    private String trackingNumber;
    private Date dateDelivered;

    public Shipping() {}

    public Long getShippingId() {
        return shippingId;
    }
    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }
    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }
}
