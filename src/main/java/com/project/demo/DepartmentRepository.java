package com.project.demo;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String>{
     Optional<Department> findById(String id);
     List<Department> findAll();
}
