<%-- 学生別成績参照JSP --%>
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
				学生別成績参照
			</h2>

			<c:choose>

				<c:when test="${scores.size() > 0}">

					<div class="mb-2">
						学生番号：${studentNo}
					</div>

					<div class="mb-2">
						学生名：${studentName}
					</div>

					<div>
						検索結果：${scores.size()}件
					</div>

					<table class="table table-hover">

						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>得点</th>
						</tr>

						<c:forEach var="score" items="${scores}">

							<tr>
								<td>${score.subjectName}</td>
								<td>${score.subjectCd}</td>
								<td>${score.testNo}</td>
								<td>${score.point}</td>
							</tr>

						</c:forEach>

					</table>

				</c:when>

				<c:otherwise>

					<div>
						成績情報が存在しませんでした。
					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>