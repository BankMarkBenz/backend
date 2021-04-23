package sit.integrated.int221project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.int221project.models.Brands;

public interface BrandsRepository extends JpaRepository<Brands, String> {
}
