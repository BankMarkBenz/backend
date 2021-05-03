package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/image")
public class ProductImageController{

    @Autowired
    private ProductsRepository productRepository;

    @GetMapping("/get/{id:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id) throws IOException {
        FileInputStream fi = new FileInputStream("picture"+id);
        byte[] image = fi.readAllBytes();
        fi.close();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @PostMapping ("/add/{id}")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file, @PathVariable("id")String id)throws IOException{
        File myFile = new File("picture" + id);
        System.out.println("1111");
        if(myFile.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
        }
        return  new ResponseEntity<>("The File Uploaded Successfully", HttpStatus.OK);
    }

    @PutMapping("/edit/{id:.+}")
    public ResponseEntity<Object> changeImage(@RequestParam("File")MultipartFile file,@PathVariable("id")String id)throws IOException {
        FileOutputStream fos = new FileOutputStream("picture"+id);
        fos.write(file.getBytes());
        fos.close();
        return  new ResponseEntity<>("The File Change Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id:.+}")
    public void deleteImage(@PathVariable("id")String id){
        String IdString[] = id.split(":");
        int hasId = parseInt(IdString[1]);
        if (hasFoundId(hasId)){
            File myFile = new File("picture" + id);
            myFile.delete();
        }
    }

    public boolean hasFoundId(int id){
        List<Products> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductId() == id){
                return true;
            }
        }
        return false;
    }

}
