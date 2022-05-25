package com.company.rayxon.service;

import com.company.rayxon.dto.ProductTypeDTO;
import com.company.rayxon.entity.ProductTypeEntity;
import com.company.rayxon.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeService {
    final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public ProductTypeDTO create(ProductTypeDTO dto) {
        ProductTypeEntity productType = new ProductTypeEntity();
        productType.setType(dto.getType());
        productTypeRepository.save(productType);
        dto.setId(productType.getId());
        return dto;
    }

    public void delete(Integer id) {
        productTypeRepository.deleteById(id);
    }

    public void update(Integer id, String type) {
        ProductTypeEntity productType = productTypeRepository.getById(id);
        productType.setType(type);
        productTypeRepository.save(productType);
    }

    public ProductTypeEntity getById(Integer id) {
        Optional<ProductTypeEntity> productType = productTypeRepository.findById(id);
        if (productType.isPresent()) {
            return productType.get();
        }
        return null;
    }
}
