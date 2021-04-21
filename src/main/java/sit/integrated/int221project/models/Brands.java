package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brands {
    @Id @Getter
    private String BrandId;
    @Getter
    private String BrandName;
}
