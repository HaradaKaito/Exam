<%-- 科目情報削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

            <div class="px-4">
                <%-- 削除実行用のActionへPOST送信 --%>
                <form action="SubjectDeleteExecute.action" method="post">
                    
                    <p class="mt-3">
                        「${subject.name} (${subject.cd})」を削除してもよろしいですか？
                    </p>

                    <%-- どのデータを消すか特定するための隠しフィールド --%>
                    <input type="hidden" name="cd" value="${subject.cd}">

                    <div class="mt-4">
                        <button type="submit" class="btn btn-danger">削除</button>
                    </div>
                    <div class="mt-3">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>