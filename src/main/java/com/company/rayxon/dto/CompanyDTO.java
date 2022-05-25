package com.company.rayxon.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class CompanyDTO {
    private Integer id;
    private Integer mehmonlar;
    private Integer fillialar;
    private Integer taomlar;
    private Integer oshpazlar;
}
