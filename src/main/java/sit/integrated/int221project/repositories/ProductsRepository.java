package sit.integrated.int221project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.int221project.models.Products;

public interface ProductsRepository extends JpaRepository<Products, String> {
}

