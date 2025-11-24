Entity
@Table(name = "CLOTHING")
@PrimaryKeyJoinColumn(name = "PRODUCTID")
public class Clothing extends Product {

    @Column(name = "SIZELABEL")
    private String sizeLabel;

    @Column(name = "MATERIAL")
    private String material;

    @Column(name = "GENDERCATEGORY")
    private String genderCategory;

    // Getters + setters
}
