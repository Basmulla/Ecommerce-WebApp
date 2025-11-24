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
    private java.sql.Date deliveryDate;

    @Column(name = "STATUS")
    private String status;

    // Getters + setters
}
