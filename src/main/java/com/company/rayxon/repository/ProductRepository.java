package com.company.rayxon.repository;

import com.company.rayxon.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCount(Integer count);

    @Query("select p from ProductEntity p where p.name=?1")
    ProductEntity searchAllByName(String object);

    @Query("select p.name,p.image from ProductEntity p")
    List<ProductEntity> getAll();

    @Query("select count(p) from WorkerEntity p")
    Integer countAll();

    ProductEntity findTopBy();

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByPrice(Long price);

}
