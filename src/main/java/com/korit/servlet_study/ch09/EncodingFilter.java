package com.korit.servlet_study.ch09;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@WebFilter("/ch09/student")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // 인코딩 과정
        String encoding = StandardCharsets.UTF_8.name();
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        servletResponse.setContentType("application/json");

        filterChain.doFilter(servletRequest, servletResponse); // 서블릿으로 넘어가기
    }
}
