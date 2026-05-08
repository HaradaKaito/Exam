package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 科目コード取得
        String cd = request.getParameter("cd");

        // null対策
        if (cd == null || cd.isEmpty()) {
            response.sendRedirect("SubjectList.action");
            return;
        }

        // 削除
        SubjectDao dao = new SubjectDao();
        dao.delete(cd);

        // 一覧へ戻る
        response.sendRedirect("SubjectList.action");
    }
}