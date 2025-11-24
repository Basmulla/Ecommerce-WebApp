package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @Column(name = "OrderID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "OrderTotal")
    private Double orderTotal;

    @ManyToOne
    @JoinColumn(name = "StaffID")
    private Staff staff;
}
