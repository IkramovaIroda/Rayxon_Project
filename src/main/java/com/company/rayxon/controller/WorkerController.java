package com.company.rayxon.controller;

import com.company.rayxon.dto.WorkerDTO;
import com.company.rayxon.entity.WorkerEntity;
import com.company.rayxon.repository.WorkerRepository;
import com.company.rayxon.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WorkerController {
    final
    WorkerService workerService;
    final
    WorkerRepository workerRepository;

    public WorkerController(WorkerService workerService, WorkerRepository workerRepository) {
        this.workerService = workerService;
        this.workerRepository = workerRepository;
    }


    @PostMapping("/worker/")
    public ResponseEntity<?> create(@RequestBody WorkerDTO dto) {
        WorkerDTO response = workerService.create(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/worker/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkerDTO dto,@PathVariable("id") Integer id) {
        workerService.update(dto,id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/worker/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        WorkerEntity productEntity = workerService.findById(id);
        return ResponseEntity.ok(productEntity);
    }

    @GetMapping("/worker/getAll")
    public ResponseEntity<?> getById() {
        List<WorkerEntity> list=workerRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/worker/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        workerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
