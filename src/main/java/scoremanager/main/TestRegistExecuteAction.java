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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String[] studentNos = request.getParameterValues("studentNo");
        String[] points = request.getParameterValues("point");
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectId");
        String countStr = request.getParameter("count");

        // DAO生成
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        TestDao testDao = new TestDao();

        Subject subject = subjectDao.get(subjectCd, school);
        int no = Integer.parseInt(countStr);

        // --- 修正のコア部分 ---
        Map<String, String> errors = new HashMap<>(); // 行ごとのエラーを保存
        List<Test> saveList = new ArrayList<>();      // 保存用リスト

        for (int i = 0; i < studentNos.length; i++) {
            String studentNo = studentNos[i];
            String pointStr = points[i];

            if (pointStr == null || pointStr.isEmpty()) {
                continue; // 未入力は保存対象外としてスキップ
            }

            try {
                int point = Integer.parseInt(pointStr);

                if (point < 0 || point > 100) {
                    // 範囲外エラーをMapに登録
                    errors.put(studentNo, "0〜100の範囲で入力してください");
                } else {
                    // 正常なデータは保存リストへ
                    Student student = studentDao.get(Integer.parseInt(entYear), classNum, studentNo, school);
                    Test test = new Test();
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setClassNum(classNum);
                    test.setNo(no);
                    test.setPoint(point);
                    saveList.add(test);
                }
            } catch (NumberFormatException e) {
                errors.put(studentNo, "数値を入力してください");
            }
        }

        // 1つでもエラーがあれば再表示
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors); // MapをJSPへ

            // 再表示に必要なデータをセット
            List<Test> testList = testDao.filter(Integer.parseInt(entYear), classNum, subject, no, school);
            request.setAttribute("students", testList);
            request.setAttribute("entYear", entYear);
            request.setAttribute("classNum", classNum);
            request.setAttribute("subjectId", subjectCd);
            request.setAttribute("subjectName", subject.getName());
            request.setAttribute("count", no);
            request.setAttribute("class_num_set", classNumDao.filter(school));
            request.setAttribute("subject_set", subjectDao.filter(school));

            List<Integer> entYearList = new ArrayList<>();
            int year = LocalDate.now().getYear();
            for (int y = year - 10; y <= year; y++) entYearList.add(y);
            request.setAttribute("ent_year_set", entYearList);

            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        // 保存実行
        boolean result = testDao.save(saveList);

        if (result) {
            request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "成績登録に失敗しました。");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        }
    }
}