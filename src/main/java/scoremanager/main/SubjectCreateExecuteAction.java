package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // パラメータ取得
        String schoolCd = request.getParameter("schoolCd");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // null対策
        if (schoolCd == null || schoolCd.isEmpty()) {
            schoolCd = "oom";
        }

        // 科目コードチェック
        if (cd == null || cd.isEmpty()) {

            request.setAttribute("error", "科目コードを入力してください");

            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);

            return;
        }

        // 3文字固定
        if (cd.length() != 3) {

            request.setAttribute("error", "科目コードは3文字で入力してください");

            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);

            return;
        }

        // 科目名チェック
        if (name == null || name.isEmpty()) {

            request.setAttribute("error", "科目名を入力してください");

            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);

            return;
        }

        // Beanへセット
        Subject subject = new Subject();

        subject.setSchoolCd(schoolCd);
        subject.setCd(cd);
        subject.setName(name);

        // DB登録
        SubjectDao dao = new SubjectDao();

        dao.save(subject);

        // 完了画面へ
        request.getRequestDispatcher("subject_create_done.jsp")
               .forward(request, response);
    }　
}