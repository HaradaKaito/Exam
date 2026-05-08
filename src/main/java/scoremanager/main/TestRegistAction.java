package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // クラス一覧・科目一覧を常に取得（UML：filter）
        ClassNumDao classDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        req.setAttribute("classList", classDao.filter(school));
        req.setAttribute("subjectList", subjectDao.filter(school));

        // 検索ボタンが押されていない場合は画面表示のみ
        if (req.getParameter("search") == null) {
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        // 入力値取得
        String entYear = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_code");
        String noStr = req.getParameter("times");

        // 入力チェック（UML の alt 条件）
        if (entYear == null || entYear.isEmpty() ||
            classNum == null || classNum.isEmpty() ||
            subjectCd == null || subjectCd.isEmpty() ||
            noStr == null || noStr.isEmpty()) {

            req.setAttribute("error", "入学年度・クラス・科目・回数を選択してください。");
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        int no = Integer.parseInt(noStr);

        // Subject オブジェクト取得（UML：SubjectDao.get）
        Subject subject = subjectDao.get(subjectCd, school);

        // 成績データ取得（UML：TestDao.filter）
        TestDao testDao = new TestDao();
        List<Test> testList = testDao.filter(
                Integer.parseInt(entYear),
                classNum,
                subject,
                no,
                school
        );

        // JSP に渡す
        req.setAttribute("testList", testList);
        req.setAttribute("ent_year", entYear);
        req.setAttribute("class_num", classNum);
        req.setAttribute("subject_code", subjectCd);
        req.setAttribute("times", noStr);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
