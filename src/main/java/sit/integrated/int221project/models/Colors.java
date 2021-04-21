package sit.integrated.int221project.models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Colors {
    @Id @Getter
    private String ColorId;
    @Getter
    private String ColorName;
    @Getter
    private String ColorValue;
}
