package sit.integrated.int221project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.int221project.models.Products;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Optional<Products> findById(int id);
}

