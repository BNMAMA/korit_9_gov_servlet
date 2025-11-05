package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;

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

@WebServlet("/백년/board")
public class BoardServlet extends HttpServlet {

    List<Board> boards = new ArrayList<>();
    // 생성 될때만 사용해야 함으로 변수로 따로 뺴놓고 생성자를 통해서만 생성이 가능하도록 한다
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //stream은 통로를 만드는 역할을 한다.
        //streamReader는 통로에 서 있는 사람들을 이동하는 역할을 한다.
        // +=는 새로운 객체를 새로 만들어서 더한다
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        StringBuilder json = new StringBuilder();
//
//
//        while (true) {
//            String str = reader.readLine();
//            if (str == null) {
//                break;
//            }
//            json.append(str);
//        }
//        System.out.println(json);

        // ObjectMapper(object를 매핑 해주는 역할)
//        ObjectMapper mapper = new ObjectMapper();
        // 읽어주는 기능 있음 (readValue)
//        Board board = mapper.readValue(json.toString(), Board.class); // readValue를 할 때 noargse를 생성한다
        // json을 objectMapper를 통해서 Board클래스 형태로 변환해준다.(=new Board)

        ObjectMapper objectMapper = new ObjectMapper();
        Board board = objectMapper.readValue(req.getInputStream(), Board.class);
        System.out.println(board);
        boards.add(board);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Response response = new Response();
        resp.setContentType("application/json");
        response.setMessage("게시글 작성 완료");
        System.out.println(response);
//        resp.getWriter().println("백년아 산책가자");
        //getWriter 안에 outputstream이 포함 돼 있음

        objectMapper.writeValue(resp.getWriter(), response.getMessage());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(), boards);
        // 응답은 무조건 writevalue
    }
}
