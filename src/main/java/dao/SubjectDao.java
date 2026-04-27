package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import org.apache.tomcat.jdbc.pool.DataSource;

import bean.School;
 
public class SubjectDao {
 
    private DataSource ds = new DataSource();
 
    private Connection getConnection() throws Exception {
        return ds.getConnection();
    }
 
    // 科目コードで1件取得
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
 
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE cd = ? AND school_cd = ?"
        );
        st.setString(1, cd);
        st.setString(2, school.getCd());
 
        ResultSet rs = st.executeQuery();
 
        if (rs.next()) {
            subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(school);  // ← School オブジェクトをそのままセット
        }
 
        st.close();
        con.close();
 
        return subject;
    }
 
    // 科目一覧（School オブジェクトで絞る）
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
 
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd"
        );
        st.setString(1, school.getCd());
 
        ResultSet rs = st.executeQuery();
 
        while (rs.next()) {
            Subject s = new Subject();
            s.setCd(rs.getString("cd"));
            s.setName(rs.getString("name"));
            s.setSchool(school);  // ← School オブジェクトをセット
            list.add(s);
        }
 
        st.close();
        con.close();
 
        return list;
    }
 
    // 科目登録
    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)"
        );
        st.setString(1, subject.getCd());
        st.setString(2, subject.getName());
        st.setString(3, subject.getSchool().getCd());
 
        int line = st.executeUpdate();
 
        st.close();
        con.close();
 
        return line > 0;
    }
 
    // 科目削除
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM subject WHERE cd = ? AND school_cd = ?"
        );
        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool().getCd());
 
        int line = st.executeUpdate();
 
        st.close();
        con.close();
 
        return line > 0;
    }
}