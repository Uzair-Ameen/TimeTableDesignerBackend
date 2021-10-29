package com.project.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "TeacherID")
    private Teacher teacherID;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course courseID;

    @ManyToOne
    @JoinColumn(name = "Department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "Batch")
    private Batch batch;

    private int section;

    private int format;
}
