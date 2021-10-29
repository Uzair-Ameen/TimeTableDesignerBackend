package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByDepartmentID_Id(String id);
    List<Course> findByDepartmentID_Name(String name);
    Optional<Course> findById(String id);
}
