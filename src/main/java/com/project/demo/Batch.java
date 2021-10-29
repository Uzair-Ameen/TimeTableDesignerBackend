package com.project.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
public @Data
class Batch {
    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    private Date startdate;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date enddate;
}
