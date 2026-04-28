<%-- 成績一覧JSP（表示順変更） --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績管理
			</h2>

			<div class="my-2 text-end px-4">
				<a href="ScoreCreate.action">新規登録</a>
			</div>

			<c:choose>
				<c:when test="${test.size() > 0}">
					<div>検索結果：${test.size()}件</div>

					<table class="table table-hover">
						<tr>
							<th>学生番号</th>
							<th>学生名</th>
							<th>入学年度</th>
							<th>クラス番号</th>
							<th class="text-center">在学中</th>
							<th>学校コード</th>
							<th></th>
						</tr>
						
						<c:forEach var="test" items="${test}">
							<tr>
								<td>${test.studentNo}</td>
								<td>${test.studentName}</td>
								<td>${test.entYear}</td>
								<td>${test.classNum}</td>

								<td class="text-center">
									<c:choose>
										<c:when test="${test.attendFlag}">
											◯
										</c:when>
										<c:otherwise>
											×
										</c:otherwise>
									</c:choose>
								</td>

								<td>${test.schoolCd}</td>

								<td>
									<a href="ScoreUpdate.action?id=${test.id}">
										変更
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>

				<c:otherwise>
					<div>成績情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>

		</section>
	</c:param>
</c:import>