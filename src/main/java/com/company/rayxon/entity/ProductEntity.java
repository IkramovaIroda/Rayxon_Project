package com.company.rayxon.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",unique = true,columnDefinition="text")
    private String name;

    @Column(name = "description",columnDefinition="text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "image")
    private Attachment image;

    @Column(name = "price")
    private Integer price;

    @Column(name = "count")
    private Integer count;

    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();

    @Column(name = "exp_date")
    private LocalDateTime expDate;

    @Column(name="part")
    private Double part;

    @OneToOne
    @JoinColumn(name = "productType")
    private ProductTypeEntity productType;
}
