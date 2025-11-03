package com.korit.servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 프로토콜 Method
 * 1. Get (R : 조회)
 * - 용도: 리소스(데이터) 조회
 * - 특징: 서버로부터 데이터를 요청만 하고 수정하지 않음(단순 조회)
 * 요청 데이터(파라미터)가 URL에 포함됨 - client -> server 에 요청하는 과정 (ex: http:// test.com/users?username=test1234)
 * 브라우저 히스토리에 남음 (뒤로 가기가 가능한 이유)
 * 북마크 가능
 * 캐싱 가능
 *
 * 2. Post
 * - 생성: 새로운 리소스 생성
 * - 특징:
 * 서버에 데이터를 전송하여 새로운 리소스 생성
 * 요청 데이터가 HTTP Body에 포함됨
 * 브라우저 히스토리에 남지 않음
 * 캐싱되지 않음
 * 역등성이 있음
 *
 * 3. Put
 * - 용도: 리소스 전체 수정/ 생성
 * - 특징:
 * 리소스가 있으면 전체를 교체, 없으면 생성 (전체 데이터를 보내지 않으면 다른 데이터들은 0으로 바뀌어 버린다)
 * 전체 데이터를 전송해야함
 * (삭제하고 싶은 내용이 있으면 Put을 써야 내용이 삭제되고 적용이 됨)
 * 역등성이 있음
 *
 * 4. Patch
 * - 용도: 리소스 부분 수정
 * - 특징:
 * 리소스의 일부만 수정 (안들어온 데이터가 있으면 변경되지 않고 그대로 유지)
 * Put보다 효율적(변경할 필드만 전송)
 * (내용을 삭제를 할 경우 내용이 없다고 판단해서 적용이 안됨)
 * 역등성이 있음
 *
 * 5. Delete
 * - 용도: 리소스 삭제
 * - 특징:
 * 지정된 리소스를 삭제
 * 역등성이 있음(동일한 연산을 여러번 하더라도 처음과 같은 결과를 유지)
 *
 * 6. HEAD
 * - 용도: 리소스 존재여부 또는 메타데이터(정보) 확인
 *
 * 7. OPTIONS
 * - 용도: HTTP 메서드의 존재여부 또는 CORS 프리플라이트 요청에 사용
 *
 * 8. CONNECT (배포 시)
 * - 용도: 프록시 서버를 통한 터널리에 사용, SSL 연결에 활용됨
 *
 * 9. TRACE
 * - 용도: 디버깅, 요청 경로 루프백 테스트
 *
 *
 */

@WebServlet("/ch02/method")
public class HttpMethodServlet extends HttpServlet {
    Map<String, String> datas = new HashMap<>(Map.of(
            "name", "김준일",
            "age", "32",
            "address", "동래구"
    ));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청 들어옴");



        // 요청
        System.out.println(req.getMethod());
        // 요청 데이터(파라미터)
        System.out.println(req.getParameter("datasKey"));
        String datasKey = req.getParameter("datasKey");

        System.out.println(datas.get(datasKey));

        /// /////////////////////////////////////////////////////////////////////

        // 응답
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); //한글화
        PrintWriter out = resp.getWriter();
        out.println(datas.get(datasKey));



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청 들어옴");

        // 요청
        System.out.println(req.getMethod());
        // 요청 데이터(파라미터)
        System.out.println(req.getParameter("textData"));
        datas.put( req.getParameter("keyName"), req.getParameter("value"));
        /// //////////////////////////////////////////////////////////////////////////////
        resp.setStatus(201);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println("데이터 추가 성공!!");
    }
}
