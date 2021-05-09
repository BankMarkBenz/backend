package sit.integrated.int221project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.integrated.int221project.models.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    Optional<Products> findById(int id);
    Optional<Products> findTopByOrderByProductIdDesc();
    @Query("select productId,productName from Products ORDER BY productId ASC")
    List<Object> getProductIdAndProductName();
}

