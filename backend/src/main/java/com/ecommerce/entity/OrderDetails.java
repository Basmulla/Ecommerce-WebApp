package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @Id
    @Column(name = "OrderDetailID")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "PurchasePrice")
    private Double purchasePrice;
}
