package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.integrated.int221project.Exception.RequestException;
import sit.integrated.int221project.Exception.ResponseException;
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
    public Products showProducts(@PathVariable int id) throws RequestException{
        if( ProductsRepository.findById(id).orElse(null) == null){
            handleItemNotFoundException("Not Found Product");
            return null;
        }
        return ProductsRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Products addProducts(@RequestBody Products product){
        return ProductsRepository.save(product);
    }

    @PutMapping("/edit/{id}")
    public Products editProducts(@PathVariable int id,@RequestBody Products newproduct) throws RequestException{
        return ProductsRepository.findById(id)
                .map(product -> {
                    product.setProductName(newproduct.getProductName());
                    product.setProductDescription(newproduct.getProductDescription());
                    product.setProductManufactureddate(newproduct.getProductManufactureddate());
                    product.setProductPrice(newproduct.getProductPrice());
                    product.setBrandId(newproduct.getBrandId());
                    product.setProductColors(newproduct.getProductColors());
                    return ProductsRepository.save(product);
                })
                .orElseGet(() -> ProductsRepository.save(newproduct));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducts(@PathVariable int id) {
        try {
            ProductsRepository.deleteById(id);
        }catch(Exception e){
            handleItemNotFoundException("Not Found Item");
        }
    }

    @RequestMapping("/")
    public ResponseEntity<ResponseException> handleItemNotFoundException(String exception){
        throw new RequestException(exception);
    }

}
