package com.company.rayxon.dto;

import com.company.rayxon.entity.Attachment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class WorkerDTO {
    private Integer id;
    private String name;
    private String description;
    private MultipartFile image;
}
