package sit.integrated.int221project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import sit.integrated.int221project.repositories.ColorsRepository;
import sit.integrated.int221project.repositories.ProductsRepository;

@SpringBootTest
class Int221projectApplicationTests {

    private ProductsRepository productsRepository;
    private ColorsRepository colorsRepository;

    @Test
    void contextLoads() {
        Assert.notEmpty(productsRepository.findAll());
        Assert.notEmpty(colorsRepository.findAll());
    }

}
