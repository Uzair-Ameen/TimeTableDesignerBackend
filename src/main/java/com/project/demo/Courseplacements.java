package com.project.demo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public @Data
class Courseplacements {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "Course")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "Batch")
    private Batch batch;

    private int semester;
}
