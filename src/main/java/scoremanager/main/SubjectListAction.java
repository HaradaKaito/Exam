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
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        // セッション取得
        HttpSession session = req.getSession();

        // ログインユーザー取得
        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // 未ログイン対策
        if (teacher == null) {

            res.sendRedirect("../login.jsp");
            return;
        }

        // 学校取得
        School school = teacher.getSchool();

        // DAO生成 
        SubjectDao dao = new SubjectDao();

        // 科目一覧取得
        List<Subject> subjectList =
                dao.filter(school);

        // リクエストへセット
        req.setAttribute(
                "subjectList",
                subjectList
        );

        // JSPへフォワード
        req.getRequestDispatcher(
                "subject_list.jsp"
        ).forward(req, res);
    }
}