package dao;

import java.util.List;

import bean.Subject;
import bean.Test;

public class TestListDao {

    private final TestDao testDao;

    public TestListDao() {
        this.testDao = new TestDao();
    }

    // 全テスト取得
    public List<Test> getAllTests() throws Exception {
        return testDao.getAllTests();
    }

    // 学生ごとのテスト取得
    public List<Test> getScoresByStudent(String studentId) throws Exception {
        return testDao.getScoresByStudent(studentId);
    }

    // 科目ごとのテスト取得
    public List<Test> getScoresBySubject(Subject subject) throws Exception {
        return testDao.getScoresBySubject(subject);
    }
}