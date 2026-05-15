// =========================================
// dao/TestListStudentDao.java
// =========================================
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	private static final String baseSql =
			"SELECT " +
			"S.NO AS STUDENT_NO, " +
			"S.NAME AS STUDENT_NAME, " +
			"SUB.CD AS SUBJECT_CD, " +
			"SUB.NAME AS SUBJECT_NAME, " +
			"T.NO, " +
			"T.POINT " +

			"FROM TEST T " +

			"INNER JOIN STUDENT S " +
			"ON T.STUDENT_NO = S.NO " +

			"INNER JOIN SUBJECT SUB " +
			"ON T.SUBJECT_CD = SUB.CD " +

			"WHERE S.NO = ? " +

			"ORDER BY SUB.CD, T.NO";

	private List<TestListStudent> postFilter(
			ResultSet rs
	) throws Exception {

		List<TestListStudent> list =
				new ArrayList<>();

		while (rs.next()) {

			TestListStudent test =
					new TestListStudent();

			Student student =
					new Student();

			student.setNo(
					rs.getString("STUDENT_NO")
			);

			student.setName(
					rs.getString("STUDENT_NAME")
			);

			test.setStudent(student);

			test.setSubjectCd(
					rs.getString("SUBJECT_CD")
			);

			test.setSubjectName(
					rs.getString("SUBJECT_NAME")
			);

			test.setNum(
					rs.getInt("NO")
			);

			test.setPoint(
					rs.getInt("POINT")
			);

			list.add(test);
		}

		return list;
	}

	public List<TestListStudent> filter(
			Student student
	) throws Exception {

		Connection con =
				getConnection();

		PreparedStatement st =
				con.prepareStatement(baseSql);

		st.setString(
				1,
				student.getNo()
		);

		ResultSet rs =
				st.executeQuery();

		List<TestListStudent> list =
				postFilter(rs);

		rs.close();
		st.close();
		con.close();

		return list;
	}
}