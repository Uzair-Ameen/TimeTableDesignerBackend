package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByDepartmentID_Id(String id);
    Teacher findById(int id);
    Teacher findByName(String name);
}
