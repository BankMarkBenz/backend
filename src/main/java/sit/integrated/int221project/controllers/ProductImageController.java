package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.integrated.int221project.handler.RequestException;
import sit.integrated.int221project.handler.Response;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/image")
public class ProductImageController{

    @Autowired
    private ProductsRepository productRepository;
    private FileInputStream fi;
    private FileOutputStream fos;
    private static final String IMAGEPATH = "./image_resources/";
    private static final String name = "picture";
    private static final String commonERROR = "Image Id Not Found";
    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id){
        try {
            fi = new FileInputStream(IMAGEPATH+ name + id+".jpg");
            byte[] image = fi.readAllBytes();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        }catch(Exception e){
           throw new RequestException("Image not Found");
        }finally{
            try{
                fi.close();
            } catch (IOException e) {
                System.out.println("Can't close FileInputStream");
            }
        }
    }

    @PostMapping ("/add/{id}")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file, @PathVariable("id")String id){
        try {
            if (hasFoundId(parseInt(id))) {
                File myFile = new File(IMAGEPATH +  name + id + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf('.')));
                if (myFile.createNewFile()) {
                    fos = new FileOutputStream(myFile);
                    fos.write(file.getBytes());
                }
                return new ResponseEntity<>("The File Uploaded Successfully", HttpStatus.OK);
            }
            System.out.println("Id not found");
            throw new RequestException(commonERROR);
        }catch (Exception e){
            System.out.println("Hellotest = error = "+e);
            throw new RequestException(commonERROR);
        }finally {
            try{
                fos.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> changeImage(@RequestParam("File")MultipartFile file,@PathVariable("id")String id) {
        try{
            if(hasFoundId(parseInt(id))){
                fos = new FileOutputStream( name+id);
                fos.write(file.getBytes());
                fos.close();
                return  new ResponseEntity<>("The File Change Successfully", HttpStatus.OK);
            }
            throw new RequestException(commonERROR);
        }catch (Exception e){
            throw new RequestException(commonERROR);
        }finally {
            try{
                fos.close();
            }catch (Exception e){
                System.out.println("Exception can't Close FileOutput");
            }
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>deleteImage(@PathVariable("id")String id){
    try{
        File myFile = new File(IMAGEPATH+ name + id+".jpg");
        if(myFile.delete()){
            return new ResponseEntity<>("The File Delete Successfully", HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        throw new RequestException("Can't Delete File");
    }

    @GetMapping("/")
    public ResponseEntity<Response> handleItemNotFoundException(String exception){
        throw new RequestException(exception);
    }

    public boolean hasFoundId(int id){
        List<Products> products = productRepository.findAll();
        for (Products product : products) {
            if (product.getProductId() == id) {
                return true;
            }
        }
        return false;
    }
}
