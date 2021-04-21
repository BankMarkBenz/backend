package sit.integrated.int221project.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity @Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private String productId;
    @Getter @Setter private String productName;
    @Getter @Setter private String productDescription;
    @Getter @Setter private float productPrice;
    @Getter @Setter private Date productManufactured;
    @OneToOne @JoinColumn(name = "product_Id" , nullable = false)
    @Getter @Setter private Brands productBrands;
    @ManyToOne @JoinColumn(name = "product_Id" , nullable = false)
    @Getter @Setter private Colors productColors[];
    @Getter @Setter private byte productImage;

    public Products() {
    }
}
