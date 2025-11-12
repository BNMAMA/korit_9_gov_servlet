package com.korit.servlet_study.ch10;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
public class JDBCMain2 {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3309/student_db";
        final String USERNAME = "root";
        final String PASSWORD = "1q2w3e4r";


        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = """
               select 
                   ct.course_id ,
                   ct.course_code, 
                   ct.course_name ,
                   pt.professor_id,
                   pt.professor_name ,
                   ct.credit ,
                   ct.enrollment_capacity ,
                   ct.classroom
               from 
                    course_tb ct
               join professor_tb pt on ct.professor_id = pt.professor_id
               where 
                    ct.course_name like concat('%', ?, '%')""";
            String searchData = "프로그래밍언어론";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, searchData);
            // ' ? ' 와일드카드 위치에 값 맵핑(1 = 몇 번째 물음표인지)
            // setString은  따옴표를 알아서 감싸준다(생략 가능)
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                Map<String, Object> resultMap = Map.of(
//                        "course_id", rs.getInt("course_id"),
//                        "course_code", rs.getString("course_code"),
//                        "course_name", rs.getString("course_name"),
//                        "professor_name", rs.getString("professor_name"),
//                        "credit", rs.getInt("credit"),
//                        "enrollment_capacity", rs.getInt("enrollment_capacity"),
//                        "classroom", rs.getString("classroom")
//                );
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("course_id", rs.getInt("course_id"));
                resultMap.put("course_code", rs.getString("course_code"));
                resultMap.put("course_name", rs.getString("course_name"));
                resultMap.put("professor_name", rs.getString("professor_name"));
                resultMap.put("credit", rs.getInt("credit"));
                resultMap.put("enrollment_capacity", rs.getInt("enrollment_capacity"));
                resultMap.put("classroom", rs.getString("classroom"));
                System.out.println(resultMap);


                @Data
                @AllArgsConstructor
                class Professor {
                    private int professorId;
                    private String professorName;
                }

                @Data
                @AllArgsConstructor
                class Course {
                    private int courseId;
                    private String courseCode;
                    private String courseName;
                    private Professor professor;
                    private int credit;
                    private int enrollmentCapacity;
                    private String classroom;
                }

                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        new Professor(rs.getInt("professor_id"), rs.getString("professor_name")),
                        rs.getInt("credit"),
                        rs.getInt("enrollment_capacity"),
                        rs.getString("classroom")
                );
                System.out.println(course);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//            int id = rs.getInt("course_id");
//            System.out.println("과목id :" + id);
//            String code = rs.getString("course_code");
//            System.out.println("과목코드: " + code);
//            String name = rs.getString("professor_name");
//            System.out.println("교수이름: " + name);
//            int credit = rs.getInt("credit");
//            System.out.println("학점: " + credit);
//            int capacity = rs.getInt("enrollment_capacity");
//            System.out.println("수용인원: "+ capacity);


