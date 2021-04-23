package sit.integrated.int221project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.int221project.models.Colors;

public interface ColorsRepository extends JpaRepository<Colors,String> {
}
