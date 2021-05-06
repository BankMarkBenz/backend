package sit.integrated.int221project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sit.integrated.int221project.Exception.RequestException;
import sit.integrated.int221project.Exception.ResponseException;
import sit.integrated.int221project.models.Products;
import sit.integrated.int221project.repositories.ProductsRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/image")
public class ProductImageController{

    @Autowired
    private ProductsRepository productRepository;
    private FileInputStream fi;
    private FileOutputStream fos;
    private final String IMAGE_PATH = "./image_resources/";
    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id){
        try {
            fi = new FileInputStream(IMAGE_PATH+"picture" + id+".jpg");
            byte[] image = fi.readAllBytes();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        }catch(Exception e){
           throw new RequestException("Image not Found");
        }finally{
            try{
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @PostMapping ("/add/{id}")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file, @PathVariable("id")String id)throws IOException{
        try {
            if (hasFoundId(parseInt(id))) {
                File myFile = new File(IMAGE_PATH + "picture" + id + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")));
                if (myFile.createNewFile()) {
                    fos = new FileOutputStream(myFile);
                    fos.write(file.getBytes());
                }
                return new ResponseEntity<>("The File Uploaded Successfully", HttpStatus.OK);
            }
            throw new RequestException("Image Id Not Found");
        }catch (Exception e){
            throw new RequestException("Image Id Not Found");
        }finally {
            try{
                fos.close();
            }catch (Exception e){
                throw new RequestException("Exception can't Close FileOutput");
            }
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> changeImage(@RequestParam("File")MultipartFile file,@PathVariable("id")String id) {
        try{
            if(hasFoundId(parseInt(id))){
                fos = new FileOutputStream("picture"+id);
                fos.write(file.getBytes());
                fos.close();
                return  new ResponseEntity<>("The File Change Successfully", HttpStatus.OK);
            }
            throw new RequestException("Image Id Not Found");
        }catch (Exception e){
            throw new RequestException("Image Id Not Found");
        }finally {
            try{
                fos.close();
            }catch (Exception e){
                throw new RequestException("Exception can't Close FileOutput");
            }
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object>deleteImage(@PathVariable("id")String id){

        File myFile = new File(IMAGE_PATH+"picture" + id+".jpg");
        if(myFile.delete()){
            return new ResponseEntity<>("The File Delete Successfully", HttpStatus.OK);
        }
        throw new RequestException("Can't Delete File");
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

    @RequestMapping("/")
    public ResponseEntity<ResponseException> handleItemNotFoundException(String exception){
        throw new RequestException(exception);
    }


}
