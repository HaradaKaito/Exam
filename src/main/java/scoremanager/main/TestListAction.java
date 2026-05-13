package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestListDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    private final TestListDao testListDao;

    public TestListAction(TestListDao testListDao) {
        if (testListDao == null) throw new IllegalArgumentException("TestListDao cannot be null");
        this.testListDao = testListDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            List<Test> tests = testListDao.getAllTests();
            if (tests == null) tests = List.of();
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("testList.jsp").forward(req, res);
        } catch (ServletException | java.io.IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "テスト一覧取得中にエラーが発生しました");
        }
    }
}