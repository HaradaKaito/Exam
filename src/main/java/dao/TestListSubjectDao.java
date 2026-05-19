package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private static final String baseSql =
			"SELECT " +
			"S.ENT_YEAR, " +
			"S.NO AS STUDENT_NO, " +
			"S.NAME AS STUDENT_NAME, " +
			"S.CLASS_NUM, " +
			"T.NO, " +
			"T.POINT " +

			"FROM TEST T " +

			"INNER JOIN STUDENT S " +
			"ON T.STUDENT_NO = S.NO " +

			"WHERE T.SUBJECT_CD = ? " +
			"AND T.SCHOOL_CD = ? " +
			"AND S.ENT_YEAR = ? " +
			"AND S.CLASS_NUM = ? " +

			"ORDER BY S.NO, T.NO";

	// 入学年度一覧取得
	public List<Integer> filterEntYear(
			School school
	) throws Exception {

		List<Integer> list =
				new ArrayList<>();

		Connection con =
				getConnection();

		PreparedStatement st =
				con.prepareStatement(
						"SELECT DISTINCT ENT_YEAR " +
						"FROM STUDENT " +
						"WHERE SCHOOL_CD = ? " +
						"ORDER BY ENT_YEAR DESC"
				);

		st.setString(
				1,
				school.getCd()
		);

		ResultSet rs =
				st.executeQuery();

		while (rs.next()) {

			list.add(
					rs.getInt("ENT_YEAR")
			);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	private List<TestListSubject> postFilter(
			ResultSet rs
	) throws Exception {

		Map<String, TestListSubject> map =
				new HashMap<>();

		while (rs.next()) {

			String studentNo =
					rs.getString("STUDENT_NO");

			TestListSubject test =
					map.get(studentNo);

			if (test == null) {

				test =
						new TestListSubject();

				test.setEntYear(
						rs.getInt("ENT_YEAR")
				);

				test.setStudentNo(
						studentNo
				);

				test.setStudentName(
						rs.getString("STUDENT_NAME")
				);

				test.setClassNum(
						rs.getString("CLASS_NUM")
				);

				test.setPoints(
						new HashMap<>()
				);

				map.put(
						studentNo,
						test
				);
			}

			test.getPoints().put(
					rs.getInt("NO"),
					rs.getInt("POINT")
			);
		}

		return new ArrayList<>(map.values());
	}

	public List<TestListSubject> filter(
			int entYear,
			String classNum,
			Subject subject,
			School school
	) throws Exception {

		Connection con =
				getConnection();

		PreparedStatement st =
				con.prepareStatement(baseSql);

		st.setString(
				1,
				subject.getCd()
		);

		st.setString(
				2,
				school.getCd()
		);

		st.setInt(
				3,
				entYear
		);

		st.setString(
				4,
				classNum
		);

		ResultSet rs =
				st.executeQuery();

		List<TestListSubject> list =
				postFilter(rs);

		rs.close();
		st.close();
		con.close();

		return list;
	}
}