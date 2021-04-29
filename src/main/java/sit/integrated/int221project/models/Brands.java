package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Brands {
    @Id
    @Column(name = "brandId")
    private String BrandId;
    @Column(name = "brandName")
    private String BrandName;
}
