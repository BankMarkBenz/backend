package sit.integrated.int221project.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter @Column(name = "productId")
    private int ProductId;
    @Getter @Setter @Column(name = "productName")
    private String ProductName;
    @Getter @Setter @Column(name = "productDescription")
    private String ProductDescription;
    @Getter @Setter @Column(name = "productPrice")
    private float ProductPrice;
    @Getter @Setter @Column(name = "productManufactureddate")
    private Date ProductManufactured;
    @ManyToOne @JoinColumn(name = "brandId" , nullable = false)
    @Getter @Setter private Brands ProductBrands;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ProductColor" ,
        joinColumns = @JoinColumn(name = "productId"),
        inverseJoinColumns = @JoinColumn(name = "colorId")
    )
    @Getter @Setter private List<Colors> ProductColors;

}
