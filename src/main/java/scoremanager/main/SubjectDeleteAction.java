package scoremanager.main;

import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // POSTなら削除実行
        if ("POST".equalsIgnoreCase(request.getMethod())) {

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

        } else {

            // GET時は確認画面表示
            String cd = request.getParameter("cd");
            String name = request.getParameter("name");

            // JSPへ値渡し
            request.setAttribute("subject_cd", cd);
            request.setAttribute("subject_name", name);

            // 削除確認画面へ
            request.getRequestDispatcher("subject_delete.jsp")
                   .forward(request, response);
        }
    }
}