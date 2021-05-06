package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.integrated.int221project.models.Brands;
import sit.integrated.int221project.repositories.BrandsRepository;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandsRepository brandsRepository;

    @GetMapping("/api/brands")
    public List<Brands> listAllBrands(){
        return brandsRepository.findAll();
    }
}
