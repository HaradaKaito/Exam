package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Test;
import dao.TestListDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    private final TestListDao testListDao;

    public TestListSubjectExecuteAction(TestListDao testListDao) {
        if (testListDao == null) throw new IllegalArgumentException("TestListDao cannot be null");
        this.testListDao = testListDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            String subjectCd = req.getParameter("subjectCd");
            if (subjectCd == null || subjectCd.isEmpty()) {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "subjectCdが未入力です");
                return;
            }

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            List<Test> tests = testListDao.getScoresBySubject(subject);
            if (tests == null) tests = List.of();
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("subjectTestList.jsp").forward(req, res);

        } catch (ServletException | java.io.IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "科目テスト一覧取得中にエラーが発生しました");
        }
    }
}