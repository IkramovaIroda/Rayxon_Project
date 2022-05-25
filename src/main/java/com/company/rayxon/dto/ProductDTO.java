package com.company.rayxon.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private MultipartFile image;
    private Integer price;
    private Integer count;
    private LocalDateTime exp_date;
    private Integer productType;
}
