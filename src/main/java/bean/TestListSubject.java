// =========================================
// bean/TestListSubject.java
// =========================================
package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

	/**
	 * 学生番号
	 */
	private String studentNo;

	/**
	 * 学生名
	 */
	private String studentName;

	/**
	 * クラス
	 */
	private String classNum;

	/**
	 * 点数Map
	 */
	private Map<Integer, Integer> points;

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public Integer getPoint(int num) {

		if (points == null) {
			return null;
		}

		return points.get(num);
	}
}