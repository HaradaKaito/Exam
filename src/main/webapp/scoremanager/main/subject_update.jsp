<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp" >
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            <form action="SubjectUpdateExecute.action" method="post">
    			<div class="mb-3">
        			<label class="form-label">科目コード</label>
        			<%-- コードは変更不可（表示のみ、またはreadonly） --%>
        			<input class="form-control" type="text" name="cd" value="${subject.cd}" readonly />
    			</div>

    			<div class="mb-3">
        			<label class="form-label">科目名</label>
        			<%-- 現在の名前が初期値として入っている状態にする --%>
        			<input class="form-control" type="text" name="name" value="${subject.name}" required />
    			</div>

    			<button type="submit" class="btn btn-primary">変更</button>
    			<a href="SubjectList.action" class="ms-3">戻る</a>
			</form>
        </section>
    </c:param>
</c:import>