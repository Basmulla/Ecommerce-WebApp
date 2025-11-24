package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "ORDERID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "STAFFID")
    private Staff staff;

    @Column(name = "ORDERDATE")
    private Date orderDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORDERTOTAL")
    private Double orderTotal;
}
