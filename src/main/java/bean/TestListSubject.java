package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

    private String studentNo;
    private String studentName;
    private String classNum;
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