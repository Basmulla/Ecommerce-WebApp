package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @Column(name = "PaymentID")
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "OrderID")
    private Orders order;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "DatePaid")
    private Date datePaid;
}
