package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();

        // 更新ボタン押下時
        if (name != null) {

            // 入力チェック
            if (cd == null || cd.isEmpty()) {

                request.setAttribute("error", "科目コードがありません");

                request.getRequestDispatcher("subject_update.jsp")
                       .forward(request, response);

                return;
            }

            if (name.isEmpty()) {

                request.setAttribute("error", "科目名を入力してください");

                request.getRequestDispatcher("subject_update.jsp")
                       .forward(request, response);

                return;
            }

            // Beanへセット
            Subject subject = new Subject();

            subject.setCd(cd);
            subject.setName(name);

            // 更新
            dao.update(subject);

            // 完了画面へ
            request.getRequestDispatcher("subject_update_done.jsp")
                   .forward(request, response);

        } else {

            // 初回表示
            if (cd == null || cd.isEmpty()) {

                response.sendRedirect("SubjectList.action");
                return;
            }

            // DBから取得
            Subject subject = dao.findByCd(cd);

            // JSPへ渡す
            request.setAttribute("subject", subject);

            // 更新画面表示
            request.getRequestDispatcher("subject_update.jsp")
                   .forward(request, response);
        }
    }
}