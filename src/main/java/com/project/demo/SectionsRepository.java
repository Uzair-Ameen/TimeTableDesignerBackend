package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionsRepository extends JpaRepository<Sections, Integer> {
    List<Sections> findByDepartment_IdAndBatch_Id(String did, int bid);
}
