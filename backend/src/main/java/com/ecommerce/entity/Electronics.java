Entity
@Table(name = "ELECTRONICS")
@PrimaryKeyJoinColumn(name = "PRODUCTID")
public class Electronics extends Product {

    @Column(name = "WARRANTYPERIOD")
    private String warrantyPeriod;

    @Column(name = "POWERRATING")
    private String powerRating;

    // Getters + setters
}
