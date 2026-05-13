package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestListDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    private final TestListDao testListDao;

    public TestListStudentExecuteAction(TestListDao testListDao) {
        if (testListDao == null) throw new IllegalArgumentException("TestListDao cannot be null");
        this.testListDao = testListDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            String studentId = req.getParameter("studentId");
            if (studentId == null || studentId.isEmpty()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentIdが未入力です");
                return;
            }

            List<Test> tests = testListDao.getScoresByStudent(studentId);
            if (tests == null) tests = List.of();
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("studentTestList.jsp").forward(req, res);

        } catch (ServletException | java.io.IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "学生テスト一覧取得中にエラーが発生しました");
        }
    }
}