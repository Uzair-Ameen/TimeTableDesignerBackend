package com.project.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public @Data
class Coursetype {

    @Id
    private int id;

    private String type;
}
