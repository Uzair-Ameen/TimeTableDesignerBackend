package com.project.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
public @Data
class Timetable {

    @Id
    private int id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date starttime;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date endtime;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date effectivefrom;

    @OneToOne
    @JoinColumn(name = "Pid")
    private Placement pid;

    private int day;


}
