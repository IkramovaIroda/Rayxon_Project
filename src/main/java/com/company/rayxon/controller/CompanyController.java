package com.company.rayxon.controller;

import com.company.rayxon.dto.CompanyDTO;
import com.company.rayxon.entity.CompanyEntity;
import com.company.rayxon.repository.CompanyRepository;
import com.company.rayxon.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"}, maxAge = 4800, allowCredentials = "false")
@Controller
public class CompanyController {
    @Autowired
    CompanyService companSerice;
    @Autowired
    CompanyRepository companyRepository;

    @PostMapping(value = "/company/create",consumes = {"*/*"})
    public ResponseEntity<?> create(@RequestBody CompanyDTO dto) {
        CompanyDTO response = companSerice.create(dto);
        return ResponseEntity.ok().build();
    }

    //Statistikalar
    @GetMapping("/company/getAll")
    public ResponseEntity<?> getAll() {
        List<CompanyEntity> responce = companyRepository.findAll();
        return ResponseEntity.ok(responce);
    }

    @PutMapping("/company/update")
    public ResponseEntity<?> update(@RequestBody CompanyDTO dto, @RequestParam("id") Integer id){
        companSerice.update(dto,id);
        return ResponseEntity.ok().build();
    }
}
