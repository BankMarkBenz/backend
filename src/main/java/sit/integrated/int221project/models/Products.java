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
    @Getter @Setter @Column(name = "product_id")
    private int ProductId;
    @Getter @Setter @Column(name = "product_name")
    private String ProductName;
    @Getter @Setter @Column(name = "product_description")
    private String ProductDescription;
    @Getter @Setter @Column(name = "product_price")
    private float ProductPrice;
    @Getter @Setter @Column(name = "product_Manufactureddate")
    private Date ProductManufactured;
    @Getter @Setter @Column(name = "product_Image")
    private byte[] ProductImage;
    @ManyToOne @JoinColumn(name = "product_id" , nullable = false)
    @Getter @Setter private Brands ProductBrands;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "product_colors" ,
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    @Getter @Setter private List<Colors> ProductColors;

}
