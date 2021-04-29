package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsRepository ProductsRepository;

    @GetMapping("/all")
    public List<Products> listAllProducts(){
        return ProductsRepository.findAll();
    }

    @GetMapping("/show/{id}")
    public Products showProducts(@PathVariable int id){
        return ProductsRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Products addProducts(@RequestBody Products product){
        return ProductsRepository.save(product);
    }

    @PutMapping("/edit/{id}")
    public Products editProducts(@PathVariable int id,@RequestBody Products newProduct){
        return ProductsRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setProductDescription(newProduct.getProductDescription());
                    product.setProductManufactured(newProduct.getProductManufactured());
                    product.setProductPrice(newProduct.getProductPrice());
                    product.setProductBrands(newProduct.getProductBrands());
                    product.setProductColors(newProduct.getProductColors());
                    return ProductsRepository.save(product);
                })
                .orElseGet(() -> ProductsRepository.save(newProduct));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducts(@PathVariable int id){
        ProductsRepository.deleteById(id);
    }
}
