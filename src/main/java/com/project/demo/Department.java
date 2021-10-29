package com.project.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public @Data
class Department {
    @Id
    private String id;

    private String name;

    private String key;
}
