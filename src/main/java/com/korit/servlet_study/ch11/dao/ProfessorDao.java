package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProfessorDao {
    private final DBConnectionMgr mgr;

    public List<Professor> findAll() {
        List<Professor> professors = new ArrayList<>();

        Connection con = null; //가지고 올려면
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = mgr.getConnection(); //데이터베이스에 연결부터 해라
            String sql = """
                   select   
                       professor_id,
                       professor_name
                   from
                        professor_tb
                   order by 
                        professor_id
                   """;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); //실행해라
            while (rs.next()) {
                Professor professor = Professor.builder()
                        .professorId(rs.getInt("professor_id"))
                        .professorName(rs.getString("professor_name"))
                        .build();
                professors.add(professor);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps, rs);
        }
        return professors;

    }

}
