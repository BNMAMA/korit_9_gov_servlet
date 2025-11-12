package com.korit.servlet_study.ch09;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private int id;
    private String name;
    private int age;
    private String address;
    private String school;
}
