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

        // ログイン教師取得
        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // 学校取得
        School school = teacher.getSchool();

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // エラー格納
        Map<String, String> errors =
                new HashMap<>();

        // =====================
        // バリデーション
        // =====================

        // 科目コード未入力
        if (cd == null || cd.isEmpty()) {

            errors.put(
                    "cd",
                    "科目コードを入力してください"
            );
        }

        // 3文字チェック
        else if (cd.length() != 3) {

            errors.put(
                    "cd",
                    "科目コードは3文字で入力してください"
            );
        }

        // 重複チェック
        else {

            Subject existing =
                    dao.get(cd, school);

            if (existing != null) {

                errors.put(
                        "duplicate",
                        "科目コードが重複しています"
                );
            }
        }

        // 科目名未入力
        if (name == null || name.isEmpty()) {

            errors.put(
                    "name",
                    "科目名を入力してください"
            );
        }

        // 入力保持
        req.setAttribute("cd", cd);
        req.setAttribute("name", name);

        // =====================
        // エラー判定
        // =====================

        // エラーなし
        if (errors.isEmpty()) {

            // Subject生成
            Subject subject =
                    new Subject();

            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);

            // 保存
            boolean result =
                    dao.save(subject);

            // 保存成功
            if (result) {

                req.setAttribute(
                        "subject",
                        subject
                );

                req.getRequestDispatcher(
                        "subject_create_done.jsp"
                ).forward(req, res);

            }

            // 保存失敗
            else {

                errors.put(
                        "save",
                        "科目登録に失敗しました"
                );

                req.setAttribute(
                        "errors",
                        errors
                );

                req.getRequestDispatcher(
                        "subject_create.jsp"
                ).forward(req, res);
            }

        }

        // エラーあり
        else {

            req.setAttribute(
                    "errors",
                    errors
            );

            req.getRequestDispatcher(
                    "subject_create.jsp"
            ).forward(req, res);
        }
    }
}