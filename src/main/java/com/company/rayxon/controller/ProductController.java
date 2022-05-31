package com.company.rayxon.controller;

import com.company.rayxon.dto.ProductDTO;
import com.company.rayxon.entity.ProductEntity;
import com.company.rayxon.repository.ProductRepository;
import com.company.rayxon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
        ProductDTO response = productService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDTO dto,@PathVariable("id") Integer id) {
        productService.update(dto,id);
        return ResponseEntity.ok().build();
    }

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

    //Taomlarimiz
    @GetMapping("/product/getAll")
    public String getAll() {
//        List<ProductEntity> productEntity = productService.getAll();
//        return ResponseEntity.ok(productEntity);
        return "main.html";
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

}
