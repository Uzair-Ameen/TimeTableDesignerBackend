package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
    List<Batch> findAll();
    Batch findById(int batchId);
}
