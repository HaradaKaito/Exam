package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

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

        // Subject生成
        Subject subject = new Subject();

        subject.setCd(cd);
        subject.setSchool(school);

        // DAO生成
        SubjectDao dao = new SubjectDao();

        // 削除実行
        boolean result = dao.delete(subject);

        // 結果判定
        if (result) {

            // 一覧へ戻る
        	request.getRequestDispatcher("subject_delete_done.jsp")
            	   .forward(request, response);
        } else {

            // エラー
            request.setAttribute("error", "科目削除に失敗しました");

            request.getRequestDispatcher("error.jsp")
                   .forward(request, response);
        }
    }
}