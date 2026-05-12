package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッション取得
        HttpSession session = request.getSession();

        // ログイン中教師取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 教師の学校取得
        School school = teacher.getSchool();

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 学校別で科目取得
        List<Subject> list = dao.filter(school);

        // JSPへ渡す
        request.setAttribute("subjectList", list);

        // JSPへ遷移
        request.getRequestDispatcher("subject_list.jsp")
               .forward(request, response);
    }
}