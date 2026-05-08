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
    
    public void save(Subject subject) throws Exception {

        Connection con = getConnection();

        String sql = "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, subject.getSchoolCd());
        st.setString(2, subject.getCd());
        st.setString(3, subject.getName());

        st.executeUpdate();

        st.close();
        con.close();
    }
    
    public void delete(String cd) throws Exception {

        Connection con = getConnection();

        String sql = "DELETE FROM SUBJECT WHERE CD = ?";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, cd);

        st.executeUpdate();

        st.close();
        con.close();
    }
    
}