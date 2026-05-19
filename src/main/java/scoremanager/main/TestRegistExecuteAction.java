package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	//w
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // ============================
        // セッション・学校情報
        // ============================
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // ============================
        // パラメータ取得
        // ============================
        String[] studentNos = request.getParameterValues("studentNo");
        String[] points = request.getParameterValues("point");
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectId");
        String countStr = request.getParameter("count");

        int count = Integer.parseInt(countStr);

        // ============================
        // DAO
        // ============================
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        TestDao testDao = new TestDao();

        Subject subject = subjectDao.get(subjectCd, school);

        // ============================
        // 入力チェック（行ごと）
        // ============================
        Map<String, String> errors = new HashMap<>();
        List<Test> saveList = new ArrayList<>();

        for (int i = 0; i < studentNos.length; i++) {

            String studentNo = studentNos[i];
            String pointStr = points[i];

            // 未入力はスキップ
            if (pointStr == null || pointStr.isEmpty()) continue;

            try {
                int point = Integer.parseInt(pointStr);

                if (point < 0 || point > 100) {
                    errors.put(studentNo, "0〜100の範囲で入力してください");
                    continue;
                }

                // 正常データ → Test 作成
                Student student = studentDao.get(
                        Integer.parseInt(entYear),
                        classNum,
                        studentNo,
                        school
                );

                Test test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setClassNum(classNum);
                test.setNo(count);
                test.setPoint(point);

                saveList.add(test);

            } catch (NumberFormatException e) {
                errors.put(studentNo, "数値を入力してください");
            }
        }

        // ============================
        // エラーがある場合 → 再表示
        // ============================
        if (!errors.isEmpty()) {

            request.setAttribute("errors", errors);

            // 再表示用データ
            request.setAttribute("students",
                    testDao.filter(Integer.parseInt(entYear), classNum, subject, count, school));

            request.setAttribute("entYear", entYear);
            request.setAttribute("classNum", classNum);
            request.setAttribute("subjectId", subjectCd);
            request.setAttribute("subjectName", subject.getName());
            request.setAttribute("count", count);

            request.setAttribute("class_num_set", classNumDao.filter(school));
            request.setAttribute("subject_set", subjectDao.filter(school));

         // 入学年度一覧（現在年の前後10年）
            int currentYear = LocalDate.now().getYear();
            List<Integer> entYearList = new ArrayList<>();

            for (int y = currentYear - 10; y <= currentYear + 10; y++) {
                entYearList.add(y);
            }

            request.setAttribute("ent_year_set", entYearList);
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        // ============================
        // 保存処理
        // ============================
        boolean result = testDao.save(saveList);

        if (result) {
            request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "成績登録に失敗しました。");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        }
    }
}
