package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Student;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.*;

@RequiredArgsConstructor
public class StudentDao {
    private final DBConnectionMgr mgr;

    //지역변수는 무조건 초기화 할 것!!!
    public void insert(Student student) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = mgr.getConnection();
            String sql = """
                    insert into student_tb
                    values (default, ?, ?, ?, ?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getStudentName()); //맨 앞 숫자는 물음표의 순서를 말해준다.
            ps.setString(2, student.getPhone());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getDepartmentId());
            ps.setInt(5, student.getGrade());
            ps.setString(6, student.getMajortype());
            ps.setString(7, student.getAdmissionYear());

            if (ps.executeUpdate() < 1){  // ctrl + enter 역할을 해줌
                throw new SQLException();
            }
             rs = ps.getGeneratedKeys(); //excute된 것들을 키들을 생성해서 가지고 온다.
            //getGeneratedKeys 이거 쓸려면 -> Statement.return_generated~~필요
            while (rs.next()) {
                int studentId = rs.getInt(1);
                student.setStudentId(studentId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            mgr.freeConnection(con, ps);
        }
    }
}
