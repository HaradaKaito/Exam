<%-- 科目登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通のベースデザインを読み込み --%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル (設計書No.1) --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            <div class="px-4">
                <%-- 完了メッセージ (設計書No.2) --%>
                <%-- 画面イメージ(14:09:24)に合わせ、緑色の背景(alert-success)で強調します --%>
                <div class="alert alert-success mt-3" role="alert">
                    登録が完了しました
                </div>

                <div class="mt-4 d-flex gap-3">
                    <%-- 戻るリンク (設計書No.3) --%>
                    <a href="SubjectCreate.action">戻る</a>

                    <%-- 科目一覧リンク (設計書No.4) --%>
                    <a href="SubjectList.action">科目一覧</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>