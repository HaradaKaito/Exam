package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    public List<Subject> findAll() throws Exception {
        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = "SELECT SCHOOL_CD, CD, NAME FROM SUBJECT";
        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();

            subject.setSchoolCd(rs.getString("SCHOOL_CD"));
            subject.setCd(rs.getString("CD"));
            subject.setName(rs.getString("NAME"));

            list.add(subject);
        }

        st.close();
        con.close();

        return list;
    }
}