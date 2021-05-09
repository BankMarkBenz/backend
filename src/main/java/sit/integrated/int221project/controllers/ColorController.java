package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.integrated.int221project.models.Colors;
import sit.integrated.int221project.repositories.ColorsRepository;

import java.util.List;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class ColorController {
    @Autowired
    private ColorsRepository colorsRepository;

    @GetMapping("/api/colors")
    public List<Colors> listAllColors(){
        return colorsRepository.findAll();
    }
}
