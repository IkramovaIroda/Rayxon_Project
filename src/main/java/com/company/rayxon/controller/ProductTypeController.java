package com.company.rayxon.controller;

import com.company.rayxon.dto.ProductTypeDTO;
import com.company.rayxon.entity.ProductTypeEntity;
import com.company.rayxon.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;

    @PostMapping("/type/")
    public ResponseEntity<?> create(@RequestBody ProductTypeDTO productTypeDTO) {
        ProductTypeDTO productTypeDTO1 = productTypeService.create(productTypeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/type/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        productTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/type/{id}/{type}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @PathVariable("type") String type) {
        productTypeService.update(id, type);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        ProductTypeEntity productType = productTypeService.getById(id);
        return ResponseEntity.ok(productType);
    }
}
