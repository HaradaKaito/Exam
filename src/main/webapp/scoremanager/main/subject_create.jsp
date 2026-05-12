<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp" >
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            <form action="SubjectCreateExecute.action" method="post" class="mt-4 px-4">
                
                <%-- 科目コード入力 --%>
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <input class="form-control" type="text" name="cd" value="${cd}" maxlength="3" placeholder="科目コードを入力してください" required />
                    
                    <%-- 文字数エラー 重複エラー --%>
                    <%-- cdのエラーか、duplicateのエラーがあれば表示 --%>
                    <c:if test="${not empty errors.get('cd')}">
                        <div class="text-warning small mt-1">${errors.get("cd")}</div>
                    </c:if>    
                    <c:if test="${not empty errors.get('duplicate')}">
                        <div class="text-warning small mt-1">${errors.get("duplicate")}</div>
                    </c:if>
                </div>

                <%-- 科目名入力 --%>
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input class="form-control" type="text" name="name" value="${name}" placeholder="科目名を入力してください" maxlength="20" required />
                    
                    <%-- 科目名のエラー表示 --%>
                    <c:if test="${not empty errors.get('name')}">
                        <div class="text-warning small mt-1">${errors.get("name")}</div>
                    </c:if>
                </div>

                <%-- 登録ボタン --%>
                <div class="mt-4">
                    <button class="btn btn-primary">登録</button>
                </div>

                <%-- 戻るリンク --%>
                <div class="mt-3">
                    <a href="SubjectList.action">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>