package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
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

        // パラメータ取得
        String[] studentNos = req.getParameterValues("student_no");
        String[] points = req.getParameterValues("point");

        String entYear = req.getParameter("ent_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_code");
        int no = Integer.parseInt(req.getParameter("times"));

        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        Subject subject = subjectDao.get(subjectCd, school);

        List<Test> saveList = new ArrayList<>();

        for (int i = 0; i < studentNos.length; i++) {

            String pointStr = points[i];

            if (pointStr == null || pointStr.isEmpty()) continue;

            int point = Integer.parseInt(pointStr);

            if (point < 0 || point > 100) {
                req.setAttribute("error", "0〜100 の数値を入力してください。");
                req.getRequestDispatcher("test_regist.jsp").forward(req, res);
                return;
            }

            // Student を取得
            Student student = studentDao.get(studentNos[i]);

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

        TestDao dao = new TestDao();
        dao.save(saveList);

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}
