package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shipping")
public class Shipping {

    @Id
    @Column(name = "ShippingID")
    private Long shippingId;

    @OneToOne
    @JoinColumn(name = "OrderID")
    private Orders order;

    @Column(name = "Courier")
    private String courier;

    @Column(name = "TrackingNum")
    private String trackingNum;

    @Column(name = "DeliveryDate")
    private Date deliveryDate;

    @Column(name = "Status")
    private String status;
}
