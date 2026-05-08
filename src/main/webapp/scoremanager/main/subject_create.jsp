<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

			<form action="SubjectCreate.action" method="post" class="mt-4 px-4">
				<%-- 科目コード入力 --%>
				<div class="mb-3">
    				<label class="form-label">科目コード</label>
    				<input class="form-control" type="text" name="cd" value="${cd}" maxlength="3" required />
    				
    				<%-- 文字数エラーの表示 --%>
    				<c:if test="${not empty errors.get('cd')}">
        				<div class="text-warning small mt-1">
            				${errors.get("cd")}
        				</div>
    				</c:if>	
    
    				<%-- 重複エラーの表示 --%>
   					<c:if test="${not empty errors.get('cd')}">
        				<div class="text-warning small mt-1">
            				${errors.get("cd")}
        				</div>
    				</c:if>
				</div>

				<%-- 科目名入力 --%>
				<div class="mb-3">
					<label class="form-label" for="subject-name-input">科目名</label>
					<input class="form-control" type="text" id="subject-name-input" name="name" 
						value="${name}" placeholder="科目名を入力してください" maxlength="20" required />
					<div class="text-danger small">${errors.get("name")}</div>
				</div>
				
				<%-- フォームの直前に追加 --%>
				<c:if test="${not empty errors}">
    				<div class="alert alert-danger">
        				<c:forEach var="error" items="${errors}">
            				<div>${error.value}</div>
        				</c:forEach>
    				</div>
				</c:if>

				<%-- 登録ボタン --%>
				<div class="mt-4">
					<button class="btn btn-primary" id="register-button">登録</button>
				</div>

				<%-- 戻るリンク --%>
				<div class="mt-3">
					<a href="SubjectList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>