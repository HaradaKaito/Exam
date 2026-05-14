package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // JSP の name に合わせる
        String[] studentNos = req.getParameterValues("studentNo");
        String[] points = req.getParameterValues("point");

        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectId");
        int no = Integer.parseInt(req.getParameter("count"));

        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();

        Subject subject = subjectDao.get(subjectCd, school);

        List<Test> saveList = new ArrayList<>();

        for (int i = 0; i < studentNos.length; i++) {

            String pointStr = points[i];

            if (pointStr == null || pointStr.isEmpty()) continue;

            int point = Integer.parseInt(pointStr);

            // 0〜100 のチェック
            if (point < 0 || point > 100) {

                // エラー時は test_regist.jsp に必要な値を再セット
                req.setAttribute("error", "0〜100 の数値を入力してください。");

                // 再表示用の学生リスト
                TestDao testDao = new TestDao();
                List<Test> testList = testDao.filter(
                        Integer.parseInt(entYear),
                        classNum,
                        subject,
                        no,
                        school
                );

                req.setAttribute("students", testList);
                req.setAttribute("entYear", entYear);
                req.setAttribute("classNum", classNum);
                req.setAttribute("subjectId", subjectCd);
                req.setAttribute("subjectName", subject.getName());
                req.setAttribute("count", no);

                // セレクトボックス用
                req.setAttribute("class_num_set", classNumDao.filter(school));
                req.setAttribute("subject_set", subjectDao.filter(school));

                // 入学年度は TestRegistAction と同じ方式で生成
                List<Integer> entYearList = new ArrayList<>();
                int year = java.time.LocalDate.now().getYear();
                for (int y = year - 10; y <= year; y++) {
                    entYearList.add(y);
                }
                req.setAttribute("ent_year_set", entYearList);

                req.getRequestDispatcher("test_regist.jsp").forward(req, res);
                return;
            }

            // Student を取得（UML準拠）
            Student student = studentDao.get(
                Integer.parseInt(entYear),
                classNum,
                studentNos[i],
                school
            );

            // Test オブジェクト作成（UML準拠）
            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(classNum);
            test.setNo(no);
            test.setPoint(point);

            saveList.add(test);
        }

        // 保存
        TestDao dao = new TestDao();
        dao.save(saveList);

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
