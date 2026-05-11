package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // null対策
        if (cd == null || cd.isEmpty()) {

            response.sendRedirect("SubjectList.action");
            return;
        }

        // JSPへ渡す
        request.setAttribute("subject_cd", cd);
        request.setAttribute("subject_name", name);

        // 削除確認画面へ
        request.getRequestDispatcher("subject_delete.jsp")
               .forward(request, response);
    }
}