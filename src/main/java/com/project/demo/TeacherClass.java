package com.project.demo;

import lombok.Data;

import java.sql.Time;

public @Data
class TeacherClass {
    private int day;
    private Time start;
    private Time end;
    public TeacherClass(int day, Time start, Time end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }
}
