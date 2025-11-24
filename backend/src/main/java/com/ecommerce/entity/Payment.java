package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @Column(name = "PAYMENTID")
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "ORDERID")
    private Orders order;

    @Column(name = "PAYMENTMETHOD")
    private String paymentMethod;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "DATEPAID")
    private Date datePaid;
}
