package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseplacementsRepository extends JpaRepository<Courseplacements, Integer> {
    List<Courseplacements> findByCourse_DepartmentID_IdAndBatch_Id(String courseId, int batchId);
}
