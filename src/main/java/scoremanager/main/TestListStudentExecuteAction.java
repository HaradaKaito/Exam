package scoremanager.main;

import java.io.IOException;
import java.util.List;

import bean.Test;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    private final TestDao testDao;

    public TestListStudentExecuteAction(TestDao testDao) {
        if (testDao == null) {
            throw new IllegalArgumentException("TestDao cannot be null");
        }
        this.testDao = testDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            String studentId = req.getParameter("studentId");
            if (studentId == null || studentId.isEmpty()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "studentIdが未入力です");
                return;
            }

            List<Test> tests = testDao.getScoresByStudent(studentId);
            if (tests == null) {
                tests = List.of(); // null防止
            }

            req.setAttribute("tests", tests);
            req.getRequestDispatcher("studentTestList.jsp").forward(req, res);

        } catch (ServletException | IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "学生テスト一覧取得中にエラーが発生しました");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベース処理中にエラーが発生しました");
        }
    }
}