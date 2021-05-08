package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.integrated.int221project.handler.RequestException;
import sit.integrated.int221project.handler.Response;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/all")
    public List<Products> listAllProducts(@RequestParam Optional<Integer> page,
                                          @RequestParam Optional<String> sortBy){
        Page<Products> list = productsRepository.findAll(PageRequest.of(
                page.orElse(0),
                8,
                Sort.Direction.ASC,
                sortBy.orElse("productId")));
        return list.getContent();
    }

    @GetMapping("/show/{id}")
    public Products showProducts(@PathVariable int id) {
        if( productsRepository.findById(id).orElse(null) == null){
            throw new RequestException("Not Found Id");
        }
        return productsRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Products addProducts(@RequestBody Products product){
        return productsRepository.save(product);
    }

    @PutMapping("/edit/{id}")
    public Products editProducts(@PathVariable int id,@RequestBody Products newproduct) throws RequestException{
        return productsRepository.findById(id)
                .map(product -> {
                    product.setProductName(newproduct.getProductName());
                    product.setProductDescription(newproduct.getProductDescription());
                    product.setProductManufactureddate(newproduct.getProductManufactureddate());
                    product.setProductPrice(newproduct.getProductPrice());
                    product.setBrandId(newproduct.getBrandId());
                    product.setProductColors(newproduct.getProductColors());
                    return productsRepository.save(product);
                })
                .orElseGet(() -> productsRepository.save(newproduct));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducts(@PathVariable int id) {
            ProductImageController image = new ProductImageController();
            image.deleteImage(Integer.toString(id));
            productsRepository.deleteById(id);
    }

    @GetMapping("/last")
    public List<Products> getLastProducts(){
       return productsRepository.findTopByOrderByProductIdDesc().stream().collect(Collectors.toList());
    }

    @GetMapping("/getAllName")
    public List<String> getAllProductName(){
        return productsRepository.findAll().stream()
                .map(products -> products.getProductName())
                .collect(Collectors.toList());
    }


    @GetMapping("/")
    public ResponseEntity<Response> handleItemNotFoundException(String exception){
        throw new RequestException(exception);
    }

}
