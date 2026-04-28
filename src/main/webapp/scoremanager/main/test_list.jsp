<%-- 成績検索条件入力JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績検索
			</h2>

			<form action="ScoreList.action" method="get">
				<div class="border mx-3 p-4 rounded">

					<!-- 入学年度 -->
					<div class="mb-3">
						<label class="form-label">入学年度</label>
						<select class="form-select" name="f1" required>
							<option value="">選択してください</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>
					</div>

					<!-- クラス -->
					<div class="mb-3">
						<label class="form-label">クラス</label>
						<select class="form-select" name="f2" required>
							<option value="">選択してください</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}">${num}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 科目 -->
					<div class="mb-4">
						<label class="form-label">科目</label>
						<select class="form-select" name="f4" required>
							<option value="">選択してください</option>
							<c:forEach var="subject" items="${subject_set}">
								<option value="${subject.id}">
									${subject.name}
								</option>
							</c:forEach>
						</select>
					</div>

					<!-- ボタン -->
					<div class="text-center">
						<button type="submit" class="btn btn-primary px-5">
							検索
						</button>
					</div>

				</div>
			</form>

		</section>
	</c:param>
</c:import>