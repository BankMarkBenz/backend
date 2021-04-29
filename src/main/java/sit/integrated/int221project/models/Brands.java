package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Brands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String brandId;
    private String brandName;
}
