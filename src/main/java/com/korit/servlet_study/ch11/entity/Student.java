package com.korit.servlet_study.ch11.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int studentId;
    private String studentName;
    @JsonIgnore//json이 실행될 때 제외시킨다.
    private String phone;
    private String email;
    private int departmentId;
    private int grade;
    private String majortype;
    private String admissionYear;
}
