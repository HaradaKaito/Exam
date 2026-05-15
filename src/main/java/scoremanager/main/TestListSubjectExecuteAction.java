package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

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

		String entYearStr =
				req.getParameter("entYear");

		String classNum =
				req.getParameter("classNum");

		String subjectCd =
				req.getParameter("subject");

		// 未入力チェック
		if (entYearStr == null || entYearStr.isEmpty()
				|| classNum == null || classNum.isEmpty()
				|| subjectCd == null || subjectCd.isEmpty()) {

			req.setAttribute(
					"error",
					"入学年度・クラス・科目を選択してください"
			);

			req.getRequestDispatcher(
					"test_list.jsp"
			).forward(req, res);

			return;
		}

		int entYear =
				Integer.parseInt(entYearStr);

		SubjectDao subjectDao =
				new SubjectDao();

		Subject subject =
				subjectDao.get(subjectCd, school);

		TestListSubjectDao dao =
				new TestListSubjectDao();

		List<TestListSubject> list =
				dao.filter(
						entYear,
						classNum,
						subject,
						school
				);

		req.setAttribute(
				"list",
				list
		);

		req.setAttribute(
				"subject",
				subject
		);

		req.getRequestDispatcher(
				"test_list_subject.jsp"
		).forward(req, res);
	}
}