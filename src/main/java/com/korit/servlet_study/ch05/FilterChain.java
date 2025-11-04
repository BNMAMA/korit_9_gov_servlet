package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FilterChain {
    private List<Filter> filters;
    private Servlet servlet;
    private int currentOrder;

    public void doFilter(Request req, Response resp) {
        if (currentOrder < filters.size()) { //재귀함수의 탈출 조건
            filters.get(currentOrder++).doFilter(req, resp, this); //this = 재귀

            return;
        }


        if ("GET".equalsIgnoreCase(req.getMethod())) {
            servlet.doget(req, resp);
        } else if ("POST".equalsIgnoreCase(req.getMethod())) {
            servlet.doPost(req, resp);

        }
    }
}
