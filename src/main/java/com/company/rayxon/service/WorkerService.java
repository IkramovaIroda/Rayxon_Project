package com.company.rayxon.service;

import com.company.rayxon.dto.WorkerDTO;
import com.company.rayxon.entity.Attachment;
import com.company.rayxon.entity.WorkerEntity;
import com.company.rayxon.enums.AttachmentContent;
import com.company.rayxon.repository.AttachmentContentRepository;
import com.company.rayxon.repository.AttachmentRepository;
import com.company.rayxon.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public WorkerDTO create(WorkerDTO dto) {
        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setName(dto.getName());
        workerEntity.setDescription(dto.getDescription());
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
            workerEntity.setImage(attachment);
        }
        workerRepository.save(workerEntity);
        dto.setId(workerEntity.getId());
        return dto;
    }

    public WorkerEntity get(Integer id) {
        Optional<WorkerEntity> optional = workerRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("not found");
    }

    public WorkerEntity getAll() {
        List<WorkerEntity> optional = workerRepository.findAll();
        if (optional != null) {
            for (WorkerEntity productEntity : optional) {
                return productEntity;
            }
        }
        throw new RuntimeException("not found");
    }

    public WorkerEntity findById(Integer id) {
        Optional<WorkerEntity> optional = workerRepository.findById(id);
        if (optional != null) {
            return optional.get();
        }
        throw new RuntimeException("not found");
    }

    public String delete(Integer id) {
        workerRepository.deleteById(id);
        return "Deleted";
    }

    public void update(WorkerDTO dto,Integer id) {
        WorkerEntity productEntity = workerRepository.getById(id);

        if(productEntity.getImage()!=null) {
            if (productEntity.getImage() != null) {
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
        }else {

            productEntity.setName(dto.getName());
            productEntity.setDescription(dto.getDescription());
            workerRepository.save(productEntity);
            dto.setId(productEntity.getId());
        }
        return;
    }

    public Integer countAll() {
        Integer responce = workerRepository.countAll();
        return responce;
    }


}
