package com.company.rayxon.repository;

import com.company.rayxon.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity,Integer> {
    @Query("select count(p) from WorkerEntity p")
    Integer countAll();
}
