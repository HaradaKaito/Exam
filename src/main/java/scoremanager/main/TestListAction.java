package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

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

		// 入学年度セット（前後10年）
		int currentYear =
				java.time.Year.now().getValue();

		Integer[] entYearSet =
				new Integer[21];

		for (int i = 0; i <= 20; i++) {

			entYearSet[i] =
					currentYear - 10 + i;
		}

		req.setAttribute(
				"ent_year_set",
				entYearSet
		);

		// クラス一覧
		ClassNumDao classNumDao =
				new ClassNumDao();

		List<String> classList =
				classNumDao.filter(school);

		req.setAttribute(
				"class_num_set",
				classList
		);

		// 科目一覧
		SubjectDao subjectDao =
				new SubjectDao();

		List<Subject> subjectList =
				subjectDao.filter(school);

		req.setAttribute(
				"subject_set",
				subjectList
		);

		req.getRequestDispatcher(
				"test_list.jsp"
		).forward(req, res);
	}
}