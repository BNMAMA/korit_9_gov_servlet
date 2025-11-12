package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/ch09/student")
public class StudentServlet extends HttpServlet { //상속해야만 servlet이 가능
    private StudentRepository studentRepository ;
    private ObjectMapper objectMapper = new ObjectMapper();
//    private int autoId = 20250001;


    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = new StudentRepository();
        config.getServletContext().setAttribute("sr", studentRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");

        Student student = objectMapper.readValue(req.getReader(), Student.class);
        studentRepository.insert(student);

//        Response response = new Response();
//        response.setMessage("등록이 완료되었습니다");
//        objectMapper.writeValue(resp.getWriter(), response);
        objectMapper.writeValue(resp.getWriter(), Map.of("message", "학생 추가 완료"));


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//
//        resp.setContentType("application/json");


       // 학생 이름으로 조회
        String searchNameValue = req.getParameter("searchName"); //searchName = 키 값

        objectMapper.writeValue(resp.getWriter(), studentRepository.findAllBySearchNameValue(searchNameValue));

//        objectMapper.writeValue(resp.getWriter(), foundStudents);
//        objectMapper.writeValue(resp.getWriter(), students);



    }
}
