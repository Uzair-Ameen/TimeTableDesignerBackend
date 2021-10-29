package com.project.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public @Data
class Roomplacements {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "timetableid")
    private Timetable timetableid;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room roomid;
}
