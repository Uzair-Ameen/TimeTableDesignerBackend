package com.project.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public @Data
class Sections {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "Batch")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    private int sectioncount;
}
