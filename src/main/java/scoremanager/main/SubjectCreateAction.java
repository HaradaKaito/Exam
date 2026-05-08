package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

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

        if (cd == null || cd.isEmpty()) {
            request.setAttribute("error", "科目コードを入力してください");
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        if (name == null || name.isEmpty()) {
            request.setAttribute("error", "科目名を入力してください");
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // Beanにセット
        Subject subject = new Subject();

        subject.setSchoolCd(schoolCd);
        subject.setCd(cd);
        subject.setName(name);

        // DB登録
        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        // 一覧画面へ戻る
        response.sendRedirect("SubjectList.action");
    }
}