package com.project.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data

class Teacher {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="DepartmentID")
    private Department departmentID;
}
