package com.korit.servlet_study.ch05;

import javax.servlet.http.HttpServlet;

public class ServletB extends Servlet {

    @Override
    public void doget(Request req, Response response) {
        System.out.println("서블릿 B에서 GET 호출");
    }

    @Override
    public void doPost(Request req, Response response) {
        System.out.println("서블릿 B에서 Post 호출");
    }
}
