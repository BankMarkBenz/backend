package sit.integrated.int221project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Int221projectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Int221projectApplication.class, args);
    }
    @GetMapping("/health")
    public String Health(){
        return "Backend Service is Health !!";
    }
}
