package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    private final TestDao testDao;

    public TestListAction(TestDao testDao) {
        if (testDao == null) throw new IllegalArgumentException("TestDao cannot be null");
        this.testDao = testDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            List<Test> tests = testDao.getAllTests();
            if (tests == null) tests = List.of();
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("testList.jsp").forward(req, res);
        } catch (ServletException | java.io.IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "テスト一覧取得中にエラーが発生しました");
        }
    }
}