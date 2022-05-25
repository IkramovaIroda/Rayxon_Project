package com.company.rayxon.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer mehmonlar;
    @Column
    private Integer fillialar;
    @Column
    private Integer taomlar;
    @Column
    private Integer oshpazlar;
}
