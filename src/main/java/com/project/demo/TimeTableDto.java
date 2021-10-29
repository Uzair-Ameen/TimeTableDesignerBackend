package com.project.demo;

import lombok.Data;


import java.util.Date;


public @Data
class TimeTableDto {

    private Date starttime;


    private Date endtime;


    private Date effectivefrom;

    private int pid;

    private int day;
}
