<%-- 科目変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <form action="SubjectUpdateExecute.action" method="post">
                <div class="px-4">
                    <%-- 科目コード表示エリア --%>
                    <div class="mb-3">
                        <label class="form-label">科目コード</label>
                        <input type="text" class="form-control-plaintext" name="cd" value="${cd}" readonly>

                        <%-- システム的なエラー表示 --%>
                        <c:if test="${not empty error}">
                            <div class="text-danger small mt-1">${error}</div>
                        </c:if>
                    </div>

                    <%-- 科目名入力エリア --%>
                    <div class="mb-3">
                        <label for="subject-name-input" class="form-label">科目名</label>
                        <input type="text" class="form-control" id="subject-name-input" name="name" value="${name}" maxlength="20" required>
                        
                        <%-- エラー表示エリア --%>
                        <c:if test="${not empty errors.get('name')}">
                            <div class="text-danger small mt-1">${errors.get('name')}</div>
                        </c:if>
                    </div>

                    <%-- 変更ボタン --%>
                    <div class="mt-4">
                        <button type="submit" class="btn btn-primary">変更</button>
                    </div>
                    <%-- 戻るリンク --%>
                    <div class="mt-2">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>