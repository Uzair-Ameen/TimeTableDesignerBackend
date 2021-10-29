package com.project.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public @Data
class Room {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name="Department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "type")
    private Coursetype type;
}
