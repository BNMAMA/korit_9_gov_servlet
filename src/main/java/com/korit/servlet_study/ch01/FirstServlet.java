package com.korit.servlet_study.ch01;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet { //상속 필수(HttpServlet)
    // 추상 메소드가 없으므로 강제 구현해야 할 메소드가 없다
    // 생성(new) 하지 않음

    public FirstServlet() { // 요청이 일어났을 때!!! 최초 한번만 생성한다.(서블릿을 만들어 놓고 요청을 안할 수도 있음)
        System.out.println("FirstServlet 생성자 호출");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("초기화");
        config.getServletContext().setAttribute("age", 26);
            // 시작되고 종료 될 때까지 계속 존재 함(전역)
    }

    @Override
    public void destroy() {
        System.out.println("소멸");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("서비스 메서드 요청 들어옴");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("요청 들어옴");

    }
}
