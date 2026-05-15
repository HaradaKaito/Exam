package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		HttpSession session =
				req.getSession();

		Teacher teacher =
				(Teacher) session.getAttribute("user");

		// 画面上部再表示用
		int currentYear =
				java.time.Year.now().getValue();

		req.setAttribute(
				"ent_year_set",
				new int[] {
						currentYear - 2,
						currentYear - 1,
						currentYear
				}
		);

		ClassNumDao classNumDao =
				new ClassNumDao();

		req.setAttribute(
				"class_num_set",
				classNumDao.filter(
						teacher.getSchool()
				)
		);

		SubjectDao subjectDao =
				new SubjectDao();

		req.setAttribute(
				"subject_set",
				subjectDao.filter(
						teacher.getSchool()
				)
		);

		String studentNo =
				req.getParameter("studentNo");

		// 未入力
		if (studentNo == null || studentNo.isEmpty()) {

			req.setAttribute(
					"error",
					"学生番号を入力してください"
			);

			req.getRequestDispatcher(
					"test_list.jsp"
			).forward(req, res);

			return;
		}

		StudentDao studentDao =
				new StudentDao();

		Student student =
				studentDao.get(studentNo);

		// 存在しない
		if (student == null) {

			req.setAttribute(
					"error",
					"学生情報が存在しませんでした"
			);

			req.getRequestDispatcher(
					"test_list.jsp"
			).forward(req, res);

			return;
		}

		TestListStudentDao dao =
				new TestListStudentDao();

		List<TestListStudent> list =
				dao.filter(student);

		req.setAttribute(
				"student",
				student
		);

		req.setAttribute(
				"list",
				list
		);

		// 同じ画面へ戻す
		req.setAttribute(
				"mode",
				"student"
		);

		req.getRequestDispatcher(
				"test_list.jsp"
		).forward(req, res);
	}
}