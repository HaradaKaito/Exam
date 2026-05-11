<%-- 科目情報削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <div class="px-4">
                <form action="SubjectDeleteExecute.action" method="post">
                    <%-- 削除対象を特定するための隠しフィールド --%>
                    <input type="hidden" name="cd" value="${subject_cd}">

                    <p class="mt-3">
                        「${subject_name}(${subject_cd})」を削除してもよろしいですか
                    </p>

                    <div class="mt-4 d-flex align-items-center gap-3">
                        <button type="submit" class="btn btn-danger">
                            削除
                        </button>
                        <a href="SubjectList.action">
                            戻る
                        </a>
                    </div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>