package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        // セッション取得
        HttpSession session = req.getSession();

        Teacher teacher =
                (Teacher) session.getAttribute("user");

        School school = teacher.getSchool();

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // DAO
        SubjectDao dao = new SubjectDao();

        // エラー格納
        Map<String, String> errors =
                new HashMap<>();

        // バリデーション
        if (cd != null && cd.length() != 3) {

            errors.put(
                    "cd",
                    "科目コードは3文字で入力してください"
            );
        }

        // 入力保持
        req.setAttribute("cd", cd);
        req.setAttribute("name", name);

        // エラーなし
        if (errors.isEmpty()) {

            Subject subject = new Subject();

            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);

            dao.save(subject);

            res.sendRedirect("SubjectList.action");

        } else {

            req.setAttribute("errors", errors);

            req.getRequestDispatcher(
                    "subject_update.jsp"
            ).forward(req, res);
        }
    }
}　