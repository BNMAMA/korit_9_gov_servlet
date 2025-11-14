package com.korit.servlet_study.ch11.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Professor {
    private int professorId;
    private String professorName;
}
