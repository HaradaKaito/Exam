package scoremanager.main;

import java.util.List;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {

    // @Override 外す（親と一致しないため）
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SubjectDao dao = new SubjectDao();
        List<Subject> list = dao.findAll();

        request.setAttribute("subjectList", list);

        // 遷移はここでやる必要あり
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}