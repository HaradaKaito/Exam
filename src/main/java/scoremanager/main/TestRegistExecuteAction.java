package scoremanager.main;

import java.time.LocalDate;
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
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        // セッション取得
        HttpSession session =
                request.getSession();

        // ログイン教師取得
        Teacher teacher =
                (Teacher) session.getAttribute("user");

        // 学校取得
        School school =
                teacher.getSchool();

        // パラメータ取得
        String[] studentNos =
                request.getParameterValues("studentNo");

        String[] points =
                request.getParameterValues("point");

        String entYear =
                request.getParameter("entYear");

        String classNum =
                request.getParameter("classNum");

        String subjectCd =
                request.getParameter("subjectId");

        String countStr =
                request.getParameter("count");

        // DAO生成
        StudentDao studentDao =
                new StudentDao();

        SubjectDao subjectDao =
                new SubjectDao();

        ClassNumDao classNumDao =
                new ClassNumDao();

        TestDao testDao =
                new TestDao();

        // 科目取得
        Subject subject =
                subjectDao.get(
                        subjectCd,
                        school
                );

        // 回数
        int no =
                Integer.parseInt(countStr);

        // 保存用リスト
        List<Test> saveList =
                new ArrayList<>();

        // 学生ごと処理
        for (int i = 0;
             i < studentNos.length;
             i++) {

            String pointStr =
                    points[i];

            // 未入力はスキップ
            if (pointStr == null ||
                pointStr.isEmpty()) {

                continue;
            }

            int point =
                    Integer.parseInt(pointStr);

            // 点数チェック
            if (point < 0 ||
                point > 100) {

                request.setAttribute(
                        "error",
                        "0〜100の数値を入力してください。"
                );

                // 再表示用データ
                List<Test> testList =
                        testDao.filter(
                                Integer.parseInt(entYear),
                                classNum,
                                subject,
                                no,
                                school
                        );

                request.setAttribute(
                        "students",
                        testList
                );

                request.setAttribute(
                        "entYear",
                        entYear
                );

                request.setAttribute(
                        "classNum",
                        classNum
                );

                request.setAttribute(
                        "subjectId",
                        subjectCd
                );

                request.setAttribute(
                        "subjectName",
                        subject.getName()
                );

                request.setAttribute(
                        "count",
                        no
                );

                request.setAttribute(
                        "class_num_set",
                        classNumDao.filter(school)
                );

                request.setAttribute(
                        "subject_set",
                        subjectDao.filter(school)
                );

                // 入学年度一覧
                List<Integer> entYearList =
                        new ArrayList<>();

                int year =
                        LocalDate.now().getYear();

                for (int y = year - 10;
                     y <= year;
                     y++) {

                    entYearList.add(y);
                }

                request.setAttribute(
                        "ent_year_set",
                        entYearList
                );

                // エラー時戻る
                request.getRequestDispatcher(
                        "test_regist.jsp"
                ).forward(request, response);

                return;
            }

            // 学生取得
            Student student =
                    studentDao.get(
                            Integer.parseInt(entYear),
                            classNum,
                            studentNos[i],
                            school
                    );

            // Test生成
            Test test =
                    new Test();

            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setClassNum(classNum);
            test.setNo(no);
            test.setPoint(point);

            // Listへ追加
            saveList.add(test);
        }

        // 保存
        boolean result =
                testDao.save(saveList);

        // 保存成功
        if (result) {

            request.getRequestDispatcher(
                    "test_regist_done.jsp"
            ).forward(request, response);

        }

        // 保存失敗
        else {

            request.setAttribute(
                    "error",
                    "成績登録に失敗しました。"
            );

            request.getRequestDispatcher(
                    "test_regist.jsp"
            ).forward(request, response);
        }
    }
}