package com.company.rayxon.controller;

import com.company.rayxon.dto.WorkerDTO;
import com.company.rayxon.entity.WorkerEntity;
import com.company.rayxon.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class WorkerController {
    @Autowired
    WorkerService workerService;


    @PostMapping("/worker/")
    public ResponseEntity<?> create(@RequestBody WorkerDTO dto) {
        WorkerDTO response = workerService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkerDTO dto,@PathVariable("id") Integer id) {
        workerService.update(dto,id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        WorkerEntity productEntity = workerService.findById(id);
        return ResponseEntity.ok(productEntity);
    }

    @DeleteMapping("/worker/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        workerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
