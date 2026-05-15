package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(
			HttpServletRequest req,
			HttpServletResponse res
	) throws Exception {

		String studentNo =
				req.getParameter("studentNo");

		// 未入力チェック
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

		// 存在チェック
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

		req.getRequestDispatcher(
				"test_list_student.jsp"
		).forward(req, res);
	}
}