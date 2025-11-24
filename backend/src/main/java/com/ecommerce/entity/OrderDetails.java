@Entity
@Table(name = "ORDERDETAILS")
public class OrderDetails {

    @Id
    @Column(name = "ORDERDETAILID")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "ORDERID")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "PRODUCTID")
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PURCHASEPRICE")
    private Double purchasePrice;

    // Getters + setters
}
