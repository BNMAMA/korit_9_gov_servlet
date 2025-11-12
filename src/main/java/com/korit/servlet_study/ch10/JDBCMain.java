package com.korit.servlet_study.ch10;

import java.sql.*;

/**
 * JDBC JavaDataBaseConnection
 * */
public class JDBCMain {
    public static void main(String[] args) {
        // http://ip:port  -> http 프로토콜
        // jdbc:mysql://  -> jdbc:mysql 프로토콜
        // mysql의 port: 기본(3306), 우리가 설정(3309)

        // 프로토콜: //IP 주소: PORT번호/데이터베이스이름(스키마)

        final String URL = "jdbc:mysql://localhost:3309/student_db";
        final String USERNAME = "root";
        final String PASSWORD = "1q2w3e4r";


        try {
//            Class.forName("com.mysql.cj.jcdb.Driver"); JDBC 4버전 이상부터는 생략가능
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //1
            // 데이터 베이스 접속
            String sql = """
                    select * from student_tb where student_name ='김준일'
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);  //2 (코드 입력창)
            // prepareStatement를 실행하려면 쿼리문이 필요하다
            ResultSet rs = ps.executeQuery();  //3 (쿼리 실행 - 결과 가지고 오기)
            // excutequery문을 통해 select를 가지고 오기
            // 실행
            rs.next();
            // 커서(화살표)가 생김 -> 행 이동을 해준다.
            //rs.hasNext() -> next가 있으면 실행됨

            int studentId = rs.getInt("student_id");
            String studentname = rs.getString("student_name");
            System.out.println("id: " + studentId);
            System.out.println("name: " + studentname);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 베이스 연결에 실패했어요");
        }

    }
}
