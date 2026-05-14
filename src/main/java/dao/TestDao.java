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
            "SELECT * FROM TEST WHERE SCHOOL_CD = ?";

    /**
     * 1件取得
     */
    public Test get(
            Student student,
            Subject subject,
            School school,
            int no
    ) throws Exception {

        Connection con = getConnection();

        PreparedStatement st =
                con.prepareStatement(
                        baseSql +
                        " AND STUDENT_NO = ?" +
                        " AND SUBJECT_CD = ?" +
                        " AND \"NO\" = ?"
                );

        st.setString(1, school.getCd());
        st.setString(2, student.getNo());
        st.setString(3, subject.getCd());
        st.setInt(4, no);

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

    /**
     * ResultSet → List<Test>
     */
    private List<Test> postFilter(
            ResultSet rs,
            School school,
            Subject subject
    ) throws Exception {

        List<Test> list =
                new ArrayList<>();

        while (rs.next()) {

            Student student =
                    new Student();

            student.setNo(
                    rs.getString("STUDENT_NO")
            );

            student.setName(
                    rs.getString("NAME")
            );

            student.setEntYear(
                    rs.getInt("ENT_YEAR")
            );

            student.setClassNum(
                    rs.getString("CLASS_NUM")
            );

            student.setAttend(
                    rs.getBoolean("IS_ATTEND")
            );

            student.setSchool(school);

            Test test =
                    new Test();

            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);

            // NULL対策
            test.setNo(
                    rs.getInt("NO")
            );

            test.setPoint(
                    rs.getInt("POINT")
            );

            test.setClassNum(
                    student.getClassNum()
            );

            list.add(test);
        }

        return list;
    }

    /**
     * 検索
     * STUDENT基準でLEFT JOIN
     */
    public List<Test> filter(
            int entYear,
            String classNum,
            Subject subject,
            int no,
            School school
    ) throws Exception {

        Connection con =
                getConnection();

        PreparedStatement st =
                con.prepareStatement(

                    "SELECT " +

                    "S.NO AS STUDENT_NO, " +
                    "S.NAME, " +
                    "S.ENT_YEAR, " +
                    "S.CLASS_NUM, " +
                    "S.IS_ATTEND, " +

                    "T.POINT, " +
                    "T.NO " +

                    "FROM STUDENT S " +

                    "LEFT JOIN TEST T " +

                    "ON S.NO = T.STUDENT_NO " +
                    "AND T.SUBJECT_CD = ? " +
                    "AND T.\"NO\" = ? " +
                    "AND T.SCHOOL_CD = ? " +

                    "WHERE S.SCHOOL_CD = ? " +
                    "AND S.ENT_YEAR = ? " +
                    "AND S.CLASS_NUM = ? " +
                    "AND S.IS_ATTEND = TRUE " +

                    "ORDER BY S.NO"

                );

        st.setString(
                1,
                subject.getCd()
        );

        st.setInt(
                2,
                no
        );

        st.setString(
                3,
                school.getCd()
        );

        st.setString(
                4,
                school.getCd()
        );

        st.setInt(
                5,
                entYear
        );

        st.setString(
                6,
                classNum
        );

        ResultSet rs =
                st.executeQuery();

        List<Test> list =
                postFilter(
                        rs,
                        school,
                        subject
                );

        rs.close();
        st.close();
        con.close();

        return list;
    }

    /**
     * 一括保存
     */
    public boolean save(
            List<Test> list
    ) throws Exception {

        Connection con =
                getConnection();

        con.setAutoCommit(false);

        try {

            for (Test test : list) {

                save(test, con);
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

    /**
     * 1件保存
     */
    private boolean save(
            Test test,
            Connection con
    ) throws Exception {

        // 既存確認
        PreparedStatement st1 =
                con.prepareStatement(

                    "SELECT COUNT(*) " +
                    "FROM TEST " +

                    "WHERE STUDENT_NO = ? " +
                    "AND SUBJECT_CD = ? " +
                    "AND \"NO\" = ? " +
                    "AND SCHOOL_CD = ?"

                );

        st1.setString(
                1,
                test.getStudent().getNo()
        );

        st1.setString(
                2,
                test.getSubject().getCd()
        );

        st1.setInt(
                3,
                test.getNo()
        );

        st1.setString(
                4,
                test.getSchool().getCd()
        );

        ResultSet rs =
                st1.executeQuery();

        rs.next();

        int count =
                rs.getInt(1);

        rs.close();
        st1.close();

        // UPDATE
        if (count > 0) {

            PreparedStatement st2 =
                    con.prepareStatement(

                        "UPDATE TEST " +

                        "SET POINT = ? " +

                        "WHERE STUDENT_NO = ? " +
                        "AND SUBJECT_CD = ? " +
                        "AND \"NO\" = ? " +
                        "AND SCHOOL_CD = ?"

                    );

            st2.setInt(
                    1,
                    test.getPoint()
            );

            st2.setString(
                    2,
                    test.getStudent().getNo()
            );

            st2.setString(
                    3,
                    test.getSubject().getCd()
            );

            st2.setInt(
                    4,
                    test.getNo()
            );

            st2.setString(
                    5,
                    test.getSchool().getCd()
            );

            st2.executeUpdate();

            st2.close();

            return true;
        }

        // INSERT
        PreparedStatement st3 =
                con.prepareStatement(

                    "INSERT INTO TEST(" +
                    "STUDENT_NO, " +
                    "SUBJECT_CD, " +
                    "CLASS_NUM, " +
                    "\"NO\", " +
                    "POINT, " +
                    "SCHOOL_CD" +
                    ") VALUES (?, ?, ?, ?, ?, ?)"

                );

        st3.setString(
                1,
                test.getStudent().getNo()
        );

        st3.setString(
                2,
                test.getSubject().getCd()
        );

        st3.setString(
                3,
                test.getClassNum()
        );

        st3.setInt(
                4,
                test.getNo()
        );

        st3.setInt(
                5,
                test.getPoint()
        );

        st3.setString(
                6,
                test.getSchool().getCd()
        );

        st3.executeUpdate();

        st3.close();

        return true;
    }
}
