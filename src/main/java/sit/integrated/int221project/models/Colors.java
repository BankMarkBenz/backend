package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Colors {
    @Id
    @Getter @Column(name = "color_id")
    private String ColorId;
    @Getter @Column(name = "color_name")
    private String ColorName;
    @Getter @Column(name = "color_value")
    private String ColorValue;
}
