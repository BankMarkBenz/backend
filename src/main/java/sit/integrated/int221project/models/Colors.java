package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Colors {
    @Id
    @Getter @Column(name = "colorId")
    private String ColorId;
    @Getter @Column(name = "colorName")
    private String ColorName;
    @Getter @Column(name = "colorValue")
    private String ColorValue;
}
