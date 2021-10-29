package com.project.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
public @Data
class Course {
    @Id
    private String id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="DepartmentID")
    private Department departmentID;
    private int chours;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date effectivefrom;

    @ManyToOne
    @JoinColumn(name="type")
    private Coursetype type;
}
