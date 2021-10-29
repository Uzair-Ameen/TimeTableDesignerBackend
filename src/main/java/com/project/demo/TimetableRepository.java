package com.project.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    List<Timetable> findTimetableByPid_departmentIdAndPid_batch_id(String departmentId, int batch_id);
    List<Timetable> findTimetableByPid_teacherID_name(String name);
}
