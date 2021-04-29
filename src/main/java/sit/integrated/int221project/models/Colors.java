package sit.integrated.int221project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String colorId;
    private String colorName;
    private String colorValue;
}
