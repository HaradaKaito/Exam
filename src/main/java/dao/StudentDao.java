package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	private String baseSql =
			"select * from student where school_cd = ? ";

	/**
	 * 学生番号で1件取得
	 */
	public Student get(String no) throws Exception {

		Student student = new Student();

		Connection connection =
				getConnection();

		PreparedStatement statement =
				null;

		try {

			statement =
					connection.prepareStatement(
						"select * from student where no = ?"
					);

			statement.setString(1, no);

			ResultSet resultSet =
					statement.executeQuery();

			SchoolDao schoolDao =
					new SchoolDao();

			if (resultSet.next()) {

				student.setNo(
						resultSet.getString("no")
				);

				student.setName(
						resultSet.getString("name")
				);

				student.setEntYear(
						resultSet.getInt("ent_year")
				);

				student.setClassNum(
						resultSet.getString("class_num")
				);

				student.setAttend(
						resultSet.getBoolean("is_attend")
				);

				student.setSchool(
						schoolDao.get(
								resultSet.getString("school_cd")
						)
				);

			} else {

				student = null;
			}

		} catch (Exception e) {

			throw e;

		} finally {

			if (statement != null) {

				try {

					statement.close();

				} catch (SQLException sqle) {

					throw sqle;
				}
			}

			if (connection != null) {

				try {

					connection.close();

				} catch (SQLException sqle) {

					throw sqle;
				}
			}
		}

		return student;
	}

	/**
	 * UML準拠
	 * 入学年度・クラス番号・学生番号・学校で取得
	 */
	public Student get(
			int entYear,
			String classNum,
			String studentNo,
			School school
	) throws Exception {

		Connection con =
				getConnection();

		PreparedStatement st =
				con.prepareStatement(

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

		ResultSet rs =
				st.executeQuery();

		Student student =
				null;

		if (rs.next()) {

			student = new Student();

			student.setNo(
					rs.getString("NO")
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
		}

		rs.close();
		st.close();
		con.close();

		return student;
	}

	/**
	 * ResultSet → List<Student>
	 */
	private List<Student> postFilter(
			ResultSet resultSet,
			School school
	) throws Exception {

		List<Student> list =
				new ArrayList<>();

		try {

			while (resultSet.next()) {

				Student student =
						new Student();

				student.setNo(
						resultSet.getString("no")
				);

				student.setName(
						resultSet.getString("name")
				);

				student.setEntYear(
						resultSet.getInt("ent_year")
				);

				student.setClassNum(
						resultSet.getString("class_num")
				);

				student.setAttend(
						resultSet.getBoolean("is_attend")
				);

				student.setSchool(school);

				list.add(student);
			}

		} catch (SQLException | NullPointerException e) {

			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 入学年度・クラス・在学フラグ検索
	 */
	public List<Student> filter(
			School school,
			int entYear,
			String classNum,
			boolean isAttend
	) throws Exception {

		List<Student> list =
				new ArrayList<>();

		Connection connection =
				getConnection();

		PreparedStatement statement =
				null;

		ResultSet resultSet =
				null;

		String condition =
				"and ent_year = ? " +
				"and class_num = ? ";

		String order =
				"order by no asc";

		String conditionIsAttend =
				"";

		if (isAttend) {

			conditionIsAttend =
					"and is_attend = true ";
		}

		try {

			statement =
					connection.prepareStatement(

						baseSql +
						condition +
						conditionIsAttend +
						order

					);

			statement.setString(
					1,
					school.getCd()
			);

			statement.setInt(
					2,
					entYear
			);

			statement.setString(
					3,
					classNum
			);

			resultSet =
					statement.executeQuery();

			list =
					postFilter(
							resultSet,
							school
					);

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (connection != null) {

				connection.close();
			}
		}

		return list;
	}

	/**
	 * 入学年度・在学フラグ検索
	 */
	public List<Student> filter(
			School school,
			int entYear,
			boolean isAttend
	) throws Exception {

		List<Student> list =
				new ArrayList<>();

		Connection connection =
				getConnection();

		PreparedStatement statement =
				null;

		ResultSet resultSet =
				null;

		String condition =
				"and ent_year = ? ";

		String order =
				"order by no asc";

		String conditionIsAttend =
				"";

		if (isAttend) {

			conditionIsAttend =
					"and is_attend = true ";
		}

		try {

			statement =
					connection.prepareStatement(

						baseSql +
						condition +
						conditionIsAttend +
						order

					);

			statement.setString(
					1,
					school.getCd()
			);

			statement.setInt(
					2,
					entYear
			);

			resultSet =
					statement.executeQuery();

			list =
					postFilter(
							resultSet,
							school
					);

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (connection != null) {

				connection.close();
			}
		}

		return list;
	}

	/**
	 * 在学フラグ検索
	 */
	public List<Student> filter(
			School school,
			boolean isAttend
	) throws Exception {

		List<Student> list =
				new ArrayList<>();

		Connection connection =
				getConnection();

		PreparedStatement statement =
				null;

		ResultSet resultSet =
				null;

		String order =
				"order by no asc";

		String conditionIsAttend =
				"";

		if (isAttend) {

			conditionIsAttend =
					"and is_attend = true ";
		}

		try {

			statement =
					connection.prepareStatement(

						baseSql +
						conditionIsAttend +
						order

					);

			statement.setString(
					1,
					school.getCd()
			);

			resultSet =
					statement.executeQuery();

			list =
					postFilter(
							resultSet,
							school
					);

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (connection != null) {

				connection.close();
			}
		}

		return list;
	}

	/**
	 * 保存（INSERT / UPDATE）
	 */
	public boolean save(
			Student student
	) throws Exception {

		Connection connection =
				getConnection();

		PreparedStatement statement =
				null;

		int count =
				0;

		try {

			// UML準拠
			Student old =
					get(
						student.getEntYear(),
						student.getClassNum(),
						student.getNo(),
						student.getSchool()
					);

			// INSERT
			if (old == null) {

				statement =
						connection.prepareStatement(

							"insert into student(" +
							"no, " +
							"name, " +
							"ent_year, " +
							"class_num, " +
							"is_attend, " +
							"school_cd" +
							") values(?, ?, ?, ?, ?, ?)"

						);

				statement.setString(
						1,
						student.getNo()
				);

				statement.setString(
						2,
						student.getName()
				);

				statement.setInt(
						3,
						student.getEntYear()
				);

				statement.setString(
						4,
						student.getClassNum()
				);

				statement.setBoolean(
						5,
						student.isAttend()
				);

				statement.setString(
						6,
						student.getSchool().getCd()
				);

			}

			// UPDATE
			else {

				statement =
						connection.prepareStatement(

							"update student " +

							"set name = ?, " +
							"ent_year = ?, " +
							"class_num = ?, " +
							"is_attend = ? " +

							"where no = ? " +
							"and school_cd = ?"

						);

				statement.setString(
						1,
						student.getName()
				);

				statement.setInt(
						2,
						student.getEntYear()
				);

				statement.setString(
						3,
						student.getClassNum()
				);

				statement.setBoolean(
						4,
						student.isAttend()
				);

				statement.setString(
						5,
						student.getNo()
				);

				statement.setString(
						6,
						student.getSchool().getCd()
				);
			}

			count =
					statement.executeUpdate();

		} finally {

			if (statement != null) {

				statement.close();
			}

			if (connection != null) {

				connection.close();
			}
		}

		return count > 0;
	}
}