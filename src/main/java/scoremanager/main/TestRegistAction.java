package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void execute(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws Exception {

        HttpSession session =
                req.getSession();

        Teacher teacher =
                (Teacher) session.getAttribute("user");

        School school =
                teacher.getSchool();

        // 入学年度一覧
        LocalDate today =
                LocalDate.now();

        int year =
                today.getYear();

        List<Integer> entYearList =
                new ArrayList<>();

        for (int y = year - 10; y <= year; y++) {

            entYearList.add(y);
        }

        req.setAttribute(
                "ent_year_set",
                entYearList
        );

        // クラス・科目一覧
        ClassNumDao classDao =
                new ClassNumDao();

        SubjectDao subjectDao =
                new SubjectDao();

        req.setAttribute(
                "class_num_set",
                classDao.filter(school)
        );

        req.setAttribute(
                "subject_set",
                subjectDao.filter(school)
        );

        // 初期表示
        if (req.getParameter("search") == null) {

            req.getRequestDispatcher(
                    "test_regist.jsp"
            ).forward(req, res);

            return;
        }

        // パラメータ取得
        String entYear =
                req.getParameter("entYear");

        String classNum =
                req.getParameter("classNum");

        String subjectCd =
                req.getParameter("subjectId");

        String noStr =
                req.getParameter("count");

        // 入力チェック
        if (entYear == null ||
            entYear.isEmpty() ||

            classNum == null ||
            classNum.isEmpty() ||

            subjectCd == null ||
            subjectCd.isEmpty() ||

            noStr == null ||
            noStr.isEmpty()) {

            req.setAttribute(
                    "error",
                    "入学年度・クラス・科目・回数を選択してください。"
            );

            req.getRequestDispatcher(
                    "test_regist.jsp"
            ).forward(req, res);

            return;
        }

        int no =
                Integer.parseInt(noStr);

        // 科目取得
        Subject subject =
                subjectDao.get(
                        subjectCd,
                        school
                );

        // 成績一覧取得
        TestDao testDao =
                new TestDao();

        List<Test> testList =
                testDao.filter(
                        Integer.parseInt(entYear),
                        classNum,
                        subject,
                        no,
                        school
                );

        // JSPへ
        req.setAttribute(
                "students",
                testList
        );

        req.setAttribute(
                "entYear",
                entYear
        );

        req.setAttribute(
                "classNum",
                classNum
        );

        req.setAttribute(
                "subjectId",
                subjectCd
        );

        req.setAttribute(
                "subjectName",
                subject.getName()
        );

        req.setAttribute(
                "count",
                no
        );

        req.getRequestDispatcher(
                "test_regist.jsp"
        ).forward(req, res);
    }
}