package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlacementRepository extends JpaRepository<Placement, Integer> {
    List<Placement> findByTeacherID_name(String name);
    List<Placement> findByDepartment_Id(String name);
    List<Placement> findAll();
    Placement findById(int id);
    List<Placement> findByDepartment_IdAndBatch_Id(String departmentId, Integer batchId);
    List<Placement> findByCourseID_IdAndBatch_IdAndSectionAndFormat(String courseId, Integer batchId, Integer section, Integer format);
    List<Placement> findByDepartment_IdAndBatch_IdAndSection(String departmentId, Integer batchId, Integer section);
}
