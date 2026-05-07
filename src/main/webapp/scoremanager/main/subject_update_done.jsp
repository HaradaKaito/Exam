<%-- 科目登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- ベースデザイン読み込み --%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <div class="px-4">
                <%-- 完了メッセージ  --%>
                <div class="alert alert-success mt-3" role="alert">
                    変更が完了しました
                </div>

                <div class="mt-4">
                    <%-- 科目一覧リンク --%>
                    <a href="SubjectList.action">科目一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>