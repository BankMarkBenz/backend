package sit.integrated.int221project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity @Getter @Setter @Table(name = "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    private int ProductId;
    @Column(name = "productName")
    private String ProductName;
    @Column(name = "productDescription")
    private String ProductDescription;
    @Column(name = "productPrice")
    private float ProductPrice;
    @Column(name = "productManufactureddate")
    private LocalDate ProductManufactured;
    @ManyToOne @JoinColumn(name = "brandId" , nullable = true)
    private Brands ProductBrands;
    @OneToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ProductColor" ,
        joinColumns = @JoinColumn(name = "productId"),
        inverseJoinColumns = @JoinColumn(name = "colorId")
    )
    private List<Colors> ProductColors;

}
