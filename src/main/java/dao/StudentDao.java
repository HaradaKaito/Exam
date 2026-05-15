package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private static final String BASE_SQL =
            "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? ";

    /**
     * 学生番号で1件取得
     */
    public Student get(String no) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        Student student = null;

        try {
            st = con.prepareStatement(
                    "SELECT * FROM STUDENT WHERE NO = ?"
            );
            st.setString(1, no);

            ResultSet rs = st.executeQuery();
            SchoolDao schoolDao = new SchoolDao();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("NO"));
                student.setName(rs.getString("NAME"));
                student.setEntYear(rs.getInt("ENT_YEAR"));
                student.setClassNum(rs.getString("CLASS_NUM"));
                student.setAttend(rs.getBoolean("IS_ATTEND"));
                student.setSchool(schoolDao.get(rs.getString("SCHOOL_CD")));
            }

            rs.close();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return student;
    }

    /**
     * UML準拠：入学年度・クラス番号・学生番号・学校で取得
     */
    public Student get(int entYear, String classNum, String studentNo, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
                "SELECT * FROM STUDENT " +
                "WHERE ENT_YEAR = ? " +
                "AND CLASS_NUM = ? " +
                "AND NO = ? " +
                "AND SCHOOL_CD = ?"
        );

        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, studentNo);
        st.setString(4, school.getCd());

        ResultSet rs = st.executeQuery();

        Student student = null;

        if (rs.next()) {
            student = new Student();
            student.setNo(rs.getString("NO"));
            student.setName(rs.getString("NAME"));
            student.setEntYear(rs.getInt("ENT_YEAR"));
            student.setClassNum(rs.getString("CLASS_NUM"));
            student.setAttend(rs.getBoolean("IS_ATTEND"));
            student.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return student;
    }

    /**
     * ResultSet → List<Student>
     */
    private List<Student> postFilter(ResultSet rs, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student student = new Student();
            student.setNo(rs.getString("NO"));
            student.setName(rs.getString("NAME"));
            student.setEntYear(rs.getInt("ENT_YEAR"));
            student.setClassNum(rs.getString("CLASS_NUM"));
            student.setAttend(rs.getBoolean("IS_ATTEND"));
            student.setSchool(school);

            list.add(student);
        }

        return list;
    }

    /**
     * 入学年度・クラス・在学フラグ検索
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        Connection con = getConnection();

        String sql =
                BASE_SQL +
                "AND ENT_YEAR = ? " +
                "AND CLASS_NUM = ? " +
                (isAttend ? "AND IS_ATTEND = TRUE " : "") +
                "ORDER BY NO ASC";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());
        st.setInt(2, entYear);
        st.setString(3, classNum);

        ResultSet rs = st.executeQuery();
        List<Student> list = postFilter(rs, school);

        rs.close();
        st.close();
        con.close();

        return list;
    }

    /**
     * 入学年度・在学フラグ検索
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        Connection con = getConnection();

        String sql =
                BASE_SQL +
                "AND ENT_YEAR = ? " +
                (isAttend ? "AND IS_ATTEND = TRUE " : "") +
                "ORDER BY NO ASC";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());
        st.setInt(2, entYear);

        ResultSet rs = st.executeQuery();
        List<Student> list = postFilter(rs, school);

        rs.close();
        st.close();
        con.close();

        return list;
    }

    /**
     * 在学フラグ検索
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        Connection con = getConnection();

        String sql =
                BASE_SQL +
                (isAttend ? "AND IS_ATTEND = TRUE " : "") +
                "ORDER BY NO ASC";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();
        List<Student> list = postFilter(rs, school);

        rs.close();
        st.close();
        con.close();

        return list;
    }

    /**
     * 保存（INSERT / UPDATE）
     */
    public boolean save(Student student) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            // 既存チェック
            Student old = get(
                    student.getEntYear(),
                    student.getClassNum(),
                    student.getNo(),
                    student.getSchool()
            );

            // INSERT
            if (old == null) {
                st = con.prepareStatement(
                        "INSERT INTO STUDENT(" +
                        "NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD" +
                        ") VALUES (?, ?, ?, ?, ?, ?)"
                );

                st.setString(1, student.getNo());
                st.setString(2, student.getName());
                st.setInt(3, student.getEntYear());
                st.setString(4, student.getClassNum());
                st.setBoolean(5, student.isAttend());
                st.setString(6, student.getSchool().getCd());
            }

            // UPDATE
            else {
                st = con.prepareStatement(
                        "UPDATE STUDENT SET " +
                        "NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ? " +
                        "WHERE NO = ? AND SCHOOL_CD = ?"
                );

                st.setString(1, student.getName());
                st.setInt(2, student.getEntYear());
                st.setString(3, student.getClassNum());
                st.setBoolean(4, student.isAttend());
                st.setString(5, student.getNo());
                st.setString(6, student.getSchool().getCd());
            }

            return st.executeUpdate() > 0;

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }
}
