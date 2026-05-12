package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 科目コード取得
        String cd = request.getParameter("cd");

        // セッション取得
        HttpSession session = request.getSession();

        // ログイン教師取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校取得
        School school = teacher.getSchool();

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 対象科目取得
        Subject subject = dao.get(cd, school);

        // JSPへ渡す
        request.setAttribute("subject", subject);

        // 削除確認画面へ
        request.getRequestDispatcher("subject_delete.jsp")
               .forward(request, response);
    }
}