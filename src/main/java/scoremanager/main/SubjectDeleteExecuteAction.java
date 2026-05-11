package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 科目コード取得
        String cd = request.getParameter("cd");

        // null対策
        if (cd == null || cd.isEmpty()) {

            response.sendRedirect("SubjectList.action");
            return;
        }

        // 削除実行
        SubjectDao dao = new SubjectDao();

        dao.delete(cd);

        // 削除完了画面へ
        request.getRequestDispatcher("subject_delete_done.jsp")
               .forward(request, response);
    }
}