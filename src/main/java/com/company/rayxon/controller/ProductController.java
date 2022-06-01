package com.company.rayxon.controller;

import com.company.rayxon.dto.ProductDTO;
import com.company.rayxon.entity.ProductEntity;
import com.company.rayxon.repository.ProductRepository;
import com.company.rayxon.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
        ProductDTO response = productService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDTO dto,@PathVariable("id") Integer id) {
        productService.update(dto,id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping (value = "/uploads",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String fileUpload(@RequestParam("file") MultipartFile multipartFile,@RequestBody ProductDTO dto) throws IOException {
//        ProductDTO response = productService.create(dto,multipartFile);
//        File convert=new File("G:/image/"+multipartFile.getOriginalFilename());
//        convert.createNewFile();
//
//        try(FileOutputStream fout=new FileOutputStream(convert)){
//            fout.write(multipartFile.getBytes());
//        }catch (Exception exception){
//            exception.printStackTrace();
//        }
//        return "File upload";
//    }

    @GetMapping("/product/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        ProductEntity productEntity = productService.findById(id);
        return ResponseEntity.ok(productEntity);
    }


    @GetMapping("/product/getName/{name}")
    public ResponseEntity<?> getById(@PathVariable("name") String name) {
        List<ProductEntity> productEnntity = productRepository.findByName(name);
        return ResponseEntity.ok(productEnntity);
    }

    @GetMapping("/product/top-product")
    public ResponseEntity<?> getFirst() {
        ProductEntity productEntity = productService.findFirst();
        return ResponseEntity.ok(productEntity);
    }

    @ApiOperation(value = "/product/getAll")
    @GetMapping("/product/getAll")
    public ResponseEntity<List<ProductEntity>> getAll() {
        List<ProductEntity> productEntity = productService.getAll();
        return ResponseEntity.ok(productEntity);
//        return ResponseEntity.ok();
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
