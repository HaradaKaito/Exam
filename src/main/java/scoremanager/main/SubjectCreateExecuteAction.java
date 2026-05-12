package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // リクエストパラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // セッション取得
        HttpSession session = request.getSession();

        // ログイン中教師取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 教師の学校取得
        School school = teacher.getSchool();

        // Subject生成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 保存
        boolean result = dao.save(subject);

        // 保存結果判定
        if (result) {

            // 一覧へリダイレクト
            response.sendRedirect("SubjectList.action");

        } else {

            // 失敗時
            request.setAttribute("error", "科目登録に失敗しました");

            request.getRequestDispatcher("subject_create.jsp")
                   .forward(request, response);
        }
    }
}