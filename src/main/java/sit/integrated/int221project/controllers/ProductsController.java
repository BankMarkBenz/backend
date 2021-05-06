package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.integrated.int221project.Exception.RequestException;
import sit.integrated.int221project.Exception.ResponseException;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import sit.integrated.int221project.controllers.ProductImageController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsRepository ProductsRepository;

    @GetMapping("/all")
    public List<Products> listAllProducts(@RequestParam Optional<Integer> page,
                                          @RequestParam Optional<String> sortBy){
        Page<Products> plist = ProductsRepository.findAll(PageRequest.of(
                page.orElse(0),
                8,
                Sort.Direction.ASC,
                sortBy.orElse("productId")));
        List<Products> llist = plist.getContent();
        return llist;
    }

    @GetMapping("/show/{id}")
    public Products showProducts(@PathVariable int id) {
        if( ProductsRepository.findById(id).orElse(null) == null){
            throw new RequestException("Not Found Id");
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
            ProductImageController w = new ProductImageController();
            w.deleteImage(Integer.toString(id));
            ProductsRepository.deleteById(id);
    }

    @RequestMapping("/")
    public ResponseEntity<ResponseException> handleItemNotFoundException(String exception){
        throw new RequestException(exception);
    }

}
