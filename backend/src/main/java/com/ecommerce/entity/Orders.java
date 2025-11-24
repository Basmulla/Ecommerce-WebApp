@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "ORDERID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @Column(name = "ORDERDATE")
    private java.sql.Date orderDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ORDERTOTAL")
    private Double orderTotal;

    @ManyToOne
    @JoinColumn(name = "STAFFID")
    private Staff staff;

    // Getters + setters
}
