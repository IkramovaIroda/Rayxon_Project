package com.company.rayxon.service;

import com.company.rayxon.dto.ProductDTO;
import com.company.rayxon.entity.Attachment;
import com.company.rayxon.entity.ProductEntity;
import com.company.rayxon.entity.ProductTypeEntity;
import com.company.rayxon.enums.AttachmentContent;
import com.company.rayxon.repository.AttachmentContentRepository;
import com.company.rayxon.repository.AttachmentRepository;
import com.company.rayxon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    ProductTypeService productTypeService;

    public ProductDTO create(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        ProductTypeEntity productType = productTypeService.getById(dto.getProductType());
        productEntity.setName(dto.getName());
        productEntity.setPrice(dto.getPrice());
        productEntity.setCount(dto.getCount());
        productEntity.setDescription(dto.getDescription());
        productEntity.setProductType(productType);
        productEntity.setExpDate(dto.getExp_date());
        if (dto.getImage() != null) {
            Attachment attachment = new Attachment();
            attachment.setName(dto.getImage().getOriginalFilename());
            attachment.setSize(dto.getImage().getSize());
            attachment.setContent_type(dto.getImage().getContentType());
            Attachment save = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(save);
            try {
                attachmentContent.setBytes(dto.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachmentContentRepository.save(attachmentContent);
            productEntity.setImage(attachment);
        }
        productEntity.setProductType(productType);

        productRepository.save(productEntity);
        dto.setId(productEntity.getId());
        return dto;
    }

    public ProductEntity get(Integer id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("not found");
    }

    public List<ProductEntity> getAll() {
        List<ProductEntity> optional = productRepository.findAll();
        return optional;
    }


    public ProductEntity findById(Integer id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional != null) {
            return optional.get();
        }
        throw new RuntimeException("not found");
    }

    public ProductEntity findFirst() {
        ProductEntity optional = productRepository.findTopBy();
        return optional;
    }

    public String delete(Integer id) {
        productRepository.deleteById(id);
        return "Deleted";
    }

    public void update(ProductDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        ProductTypeEntity productType = productTypeService.getById(dto.getProductType());
        if (dto.getImage().isEmpty()) {
            productEntity.setImage(null);
        } else {
            if (productEntity.getImage() == null) {
                Attachment attachment = new Attachment();
                attachment.setName(dto.getImage().getOriginalFilename());
                attachment.setSize(dto.getImage().getSize());
                attachment.setContent_type(dto.getImage().getContentType());
                Attachment save = attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent();
                attachmentContent.setAttachment(save);
                try {
                    attachmentContent.setBytes(dto.getImage().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachmentContentRepository.save(attachmentContent);
                productEntity.setImage(attachment);
            } else {
                Optional<Attachment> optionalAttachment = attachmentRepository.findById(productEntity.getImage().getId());
                if (optionalAttachment.isEmpty()) return;
                Attachment attachment = optionalAttachment.get();
                attachment.setName(dto.getImage().getOriginalFilename());
                attachment.setSize(dto.getImage().getSize());
                attachment.setContent_type(dto.getImage().getContentType());
                Attachment save = attachmentRepository.save(attachment);
                Optional<AttachmentContent> optionalAttachmentContent =
                        attachmentContentRepository.findByAttachment_id(save.getId());
                AttachmentContent attachmentContent;
                if (optionalAttachmentContent.isEmpty()) {
                    attachmentContent = new AttachmentContent();
                } else {
                    attachmentContent = optionalAttachmentContent.get();
                }
                try {
                    attachmentContent.setBytes(dto.getImage().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                attachmentContentRepository.save(attachmentContent);
            }
        }
        productEntity.setPrice(dto.getPrice());
        productEntity.setExpDate(dto.getExp_date());
        productEntity.setName(dto.getName());
        productEntity.setProductType(productType);
        productRepository.save(productEntity);
        return;
    }

    public Integer countAll() {
        Integer responce = productRepository.countAll();
        return responce;
    }


}
