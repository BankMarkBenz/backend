package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brands {
    @Id
    @Getter @Column(name = "brand_id")
    private String BrandId;
    @Getter @Column(name = "brand_name")
    private String BrandName;
}
