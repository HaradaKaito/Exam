package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

    // @Override 外す
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String schoolCd = request.getParameter("schoolCd");
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Subject subject = new Subject();

        subject.setSchoolCd(schoolCd);
        subject.setCd(cd);
        subject.setName(name);

        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        // 一覧へ戻る
        response.sendRedirect("SubjectList.action");
    }
}