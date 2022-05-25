package com.company.rayxon.service;

import com.company.rayxon.dto.CompanyDTO;
import com.company.rayxon.entity.CompanyEntity;
import com.company.rayxon.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProductService productService;
    @Autowired
    WorkerService workerService;

    public CompanyDTO create(CompanyDTO dto) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setTaomlar(productService.countAll());
        companyEntity.setOshpazlar(workerService.countAll());
        companyEntity.setFillialar(dto.getFillialar());
        companyEntity.setMehmonlar(dto.getMehmonlar());
        companyRepository.save(companyEntity);
        dto.setId(companyEntity.getId());
        return dto;
    }

    public void update(CompanyDTO dto, Integer id) {
        CompanyEntity companyEntity = companyRepository.getById(id);
        if (dto.getFillialar() == null) {
            companyEntity.setFillialar(0);
        } else
            companyEntity.setFillialar(dto.getFillialar());
        if (dto.getMehmonlar() == null) {
            companyEntity.setMehmonlar(0);
        } else
            companyEntity.setMehmonlar(dto.getMehmonlar());

        companyEntity.setOshpazlar(workerService.countAll());
        companyEntity.setTaomlar(productService.countAll());
        companyRepository.save(companyEntity);
    }


}
