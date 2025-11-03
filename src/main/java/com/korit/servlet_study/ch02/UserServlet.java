package com.korit.servlet_study.ch02;

import lombok.AllArgsConstructor;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {
    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // username == "test"
        // 찾으면 User 객체 응답(200), 못찾으면 해당 username은 존재하지 않습니다.(404)
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser = foundUsers.isEmpty() ? null : foundUsers.get(0);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (Objects.isNull(foundUser)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지 않습니다.");
            return;
        }
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println(foundUser);
//        User user = new User();
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        String username = req.getParameter("username");
//        if ("test".equals(req.getParameter(username))) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//            users.add(user);
//            System.out.println(users);
//        }
//        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        resp.getWriter().println("username은 존재하지 않습니다");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        users.add(user);
        System.out.println(users);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("사용자 등록 완료");

        @AllArgsConstructor
        class ValidException extends RuntimeException {
            Map<String, String > error;
        }
    }

//    private boolean isValid(String str) {
//        if (str == null) return false;
//        return str.isBlank();
//
//    }
}
