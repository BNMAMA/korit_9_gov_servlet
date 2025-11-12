package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebFilter("/ch09/student")
public class SecurityFilter implements Filter {
    private final String SECRET = "1234";
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String secret = request.getHeader("Secret"); //secret에 123
        System.out.println(secret);

        if (!SECRET.equals(secret)) {
            response.setStatus(401);
            objectMapper.writeValue(response.getWriter(), Map.of("message", "요청 권한이 없습니다."));
            return; //여기까지 통과해야 다음 filter로 넘어갈 수 있다.
        }

        filterChain.doFilter(servletRequest, servletResponse); //다음 필터로 넘어갈 수 있도록 해줌
        //필터의 순서는 servlet 마음대로 실행되기 때문에 반드시 web.xml로 가서 순서를 기재하여야 한다.
    }
}
