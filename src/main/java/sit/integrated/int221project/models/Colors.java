package sit.integrated.int221project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
public class Colors implements Serializable {
    @Id
    @Column(name = "colorId")
    private String ColorId;
    @Column(name = "colorName")
    private String ColorName;
    @Column(name = "colorValue")
    private String ColorValue;
}
