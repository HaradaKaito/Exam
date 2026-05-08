package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    private static final String baseSql =
        "SELECT * FROM TEST WHERE SCHOOL = ?";

    // 1件取得
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            baseSql + " AND STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?"
        );

        st.setString(1, student.getNo());
        st.setString(2, subject.getCd());
        st.setInt(3, no);

        ResultSet rs = st.executeQuery();

        Test test = null;

        if (rs.next()) {
            test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(rs.getInt("NO"));
            test.setPoint(rs.getInt("POINT"));
            test.setClassNum(student.getClassNum());
        }

        rs.close();
        st.close();
        con.close();

        return test;
    }

    // ResultSet → List<Test> 変換
    public List<Test> postFilter(ResultSet rs, School school, Subject subject) throws Exception {

        List<Test> list = new ArrayList<>();

        while (rs.next()) {

            Student student = new Student();
            student.setNo(rs.getString("STUDENT_NO"));
            student.setName(rs.getString("NAME"));
            student.setEntYear(rs.getInt("ENT_YEAR"));
            student.setClassNum(rs.getString("CLASS_NUM"));
            student.setAttend(rs.getBoolean("IS_ATTEND"));
            student.setSchool(school);

            Test t = new Test();
            t.setStudent(student);
            t.setSubject(subject);
            t.setSchool(school);
            t.setNo(rs.getInt("NO"));
            t.setPoint(rs.getInt("POINT"));
            t.setClassNum(student.getClassNum());

            list.add(t);
        }

        return list;
    }

    // 検索（入学年度・クラス・科目・回数）
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT T.*, S.NAME, S.ENT_YEAR, S.CLASS_NUM, S.IS_ATTEND " +
            "FROM TEST T " +
            "JOIN STUDENT S ON T.STUDENT_NO = S.NO " +
            "WHERE T.SCHOOL = ? AND S.ENT_YEAR = ? AND S.CLASS_NUM = ? " +
            "AND T.SUBJECT_CD = ? AND T.NO = ? " +
            "ORDER BY S.NO"
        );

        st.setString(1, school.getCd());
        st.setInt(2, entYear);
        st.setString(3, classNum);
        st.setString(4, subject.getCd());
        st.setInt(5, num);

        ResultSet rs = st.executeQuery();

        List<Test> list = postFilter(rs, school, subject);

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 複数保存
    public boolean save(List<Test> list) throws Exception {

        Connection con = getConnection();
        con.setAutoCommit(false);

        try {
            for (Test t : list) {
                save(t, con);
            }
            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback();
            throw e;

        } finally {
            con.close();
        }
    }

    // 1件保存（INSERT or UPDATE）
    public boolean save(Test test, Connection con) throws Exception {

        // 既存チェック
        PreparedStatement st1 = con.prepareStatement(
            "SELECT COUNT(*) FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL = ?"
        );

        st1.setString(1, test.getStudent().getNo());
        st1.setString(2, test.getSubject().getCd());
        st1.setInt(3, test.getNo());
        st1.setString(4, test.getSchool().getCd());

        ResultSet rs = st1.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        st1.close();

        // UPDATE
        if (count > 0) {
            PreparedStatement st2 = con.prepareStatement(
                "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ? AND SCHOOL = ?"
            );

            st2.setInt(1, test.getPoint());
            st2.setString(2, test.getStudent().getNo());
            st2.setString(3, test.getSubject().getCd());
            st2.setInt(4, test.getNo());
            st2.setString(5, test.getSchool().getCd());

            st2.executeUpdate();
            st2.close();
            return true;
        }

        // INSERT
        PreparedStatement st3 = con.prepareStatement(
            "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, NO, POINT, SCHOOL) VALUES (?, ?, ?, ?, ?)"
        );

        st3.setString(1, test.getStudent().getNo());
        st3.setString(2, test.getSubject().getCd());
        st3.setInt(3, test.getNo());
        st3.setInt(4, test.getPoint());
        st3.setString(5, test.getSchool().getCd());

        st3.executeUpdate();
        st3.close();

        return true;
    }
}
