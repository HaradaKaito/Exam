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

        String sql = "SELECT * FROM SUBJECT";
        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setSubjectId(rs.getString("SUBJECT_ID"));
            subject.setSubjectName(rs.getString("SUBJECT_NAME"));

            list.add(subject);
        }

        st.close();
        con.close();

        return list;
    }
}