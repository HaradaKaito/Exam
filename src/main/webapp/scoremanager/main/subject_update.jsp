<%-- 科目情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <form action="SubjectUpdateExecute.action" method="post" class="mt-4 px-4">
                
                <%-- 科目コード表示 (readonly) --%>
                <div class="mb-3">
                    <label class="form-label" for="subject-cd-display">科目コード</label>
                    <input class="form-control-plaintext border-bottom" type="text" id="subject-cd-display" 
                        name="cd" value="${subject.cd}" readonly />
                </div>

                <%-- 科目名入力 --%>
                <div class="mb-3">
                    <label class="form-label" for="subject-name-input">科目名</label>
                    <input class="form-control" type="text" id="subject-name-input" name="name" 
                        value="${subject.name}" placeholder="科目名を入力してください" maxlength="20" required />
                    
                    <%-- エラー表示エリア --%>
                    <c:if test="${not empty errors.get('name')}">
                        <div class="text-danger small mt-1">${errors.get("name")}</div>
                    </c:if>
                </div>

                <%-- ★ ボタンとリンクの配置修正 (13:54:38 のレイアウトを再現) --%>
                <div class="mt-4">
                    <%-- ④ 変更ボタン --%>
                    <div>
                        <button class="btn btn-primary" id="update-button">変更</button>
                    </div>

                    <%-- ⑤ 戻るリンク：mt-3で上のボタンと間隔を空けて配置 --%>
                    <div class="mt-3">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>