package com.korit.servlet_study.ch05;

import java.sql.SQLOutput;

public class Filter3 implements Filter {
    @Override //자기 자신의 주소를 넘겨줌
    public void doFilter(Request req, Response resp, FilterChain filterChain) {

        System.out.println("필터3 전처리");
        filterChain.doFilter(req, resp);
        System.out.println("필터3 후처리");

    }
}