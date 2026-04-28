<%-- 科目一覧JSP --%>
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
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>

			<%-- テーブルの外枠とヘッダ表示 --%>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>科目コード</th>
						<th>科目名</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<%-- データがある場合は行をループ表示 --%>
						<c:when test="${subjects.size() > 0}">
							<c:forEach var="subject" items="${subjects}">
								<tr>
									<td>${subject.Cd}</td>
									<td>${subject.Name}</td>
									<td><a href="SubjectUpdate.action?cd=${subject.Cd}">変更</a></td>
									<td><a href="SubjectDelete.action?cd=${subject.Cd}">削除</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<%-- データがない場合はメッセージ用の行を表示 --%>
						<c:otherwise>
							<tr>
                				<td colspan="4" class="text-center">科目情報がありません</td>
            				</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</section>
	</c:param>
</c:import>