package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // セッション取得
        HttpSession session = request.getSession();

        // ログイン教師取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校取得
        School school = teacher.getSchool();

        // Subject生成
        Subject subject = new Subject();

        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 更新保存
        boolean result = dao.save(subject);

        // 判定
        if (result) {

            // 一覧へ戻る
            response.sendRedirect("SubjectList.action");

        } else {

            // エラー
            request.setAttribute("error", "科目更新に失敗しました");

            request.setAttribute("subject", subject);

            request.getRequestDispatcher("subject_update.jsp")
                   .forward(request, response);
        }
    }
}