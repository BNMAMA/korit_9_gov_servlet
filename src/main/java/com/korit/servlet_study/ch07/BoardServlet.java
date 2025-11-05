package com.korit.servlet_study.ch07;

import com.fasterxml.jackson.databind.ObjectMapper;

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

@WebServlet("/ch07/boards")
public class BoardServlet extends HttpServlet {
    List<Board> boards = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), boards);




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder json = new StringBuilder();
        while (true) {
            String str = bufferedReader.readLine();
            if (str == null) {
                break;
            }
            json.append(str);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Board board = objectMapper.readValue(req.getInputStream(), Board.class); //board 객체 형태로 바꿈
        boards.add(board);

        Response response = new Response();
        response.setMessage("완료!");
        objectMapper.writeValue(resp.getWriter(), response.getMessage());

    }


}
