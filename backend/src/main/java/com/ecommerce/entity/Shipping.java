package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHIPPING")
public class Shipping {

    @Id
    @Column(name = "SHIPPINGID")
    private Long shippingId;

    @OneToOne
    @JoinColumn(name = "ORDERID")
    private Orders order;

    @Column(name = "COURIER")
    private String courier;

    @Column(name = "TRACKINGNUM")
    private String trackingNum;

    @Column(name = "DELIVERYDATE")
    private Date deliveryDate;

    @Column(name = "STATUS")
    private String status;
}
