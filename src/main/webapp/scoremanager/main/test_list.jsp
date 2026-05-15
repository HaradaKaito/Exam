<%-- 成績参照検索画面JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<!-- タイトル -->
			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				成績参照
			</h2>

			<div class="border rounded p-4">

				<!-- =========================
				     科目情報検索
				========================= -->

				<form action="SubjectScoreList.action" method="get">

					<div class="row align-items-end mb-4">

						<!-- ラベル -->
						<div class="col-2">
							<label class="form-label fw-bold">
								科目情報
							</label>
						</div>

						<!-- 入学年度 -->
						<div class="col-2">
							<label class="form-label">
								入学年度
							</label>

							<select class="form-select" name="entYear">

								<option value="">
									--------
								</option>

								<c:forEach var="year" items="${ent_year_set}">

									<option value="${year}">
										${year}
									</option>

								</c:forEach>

							</select>
						</div>

						<!-- クラス -->
						<div class="col-2">
							<label class="form-label">
								クラス
							</label>

							<select class="form-select" name="classNum">

								<option value="">
									--------
								</option>

								<c:forEach var="num" items="${class_num_set}">

									<option value="${num}">
										${num}
									</option>

								</c:forEach>

							</select>
						</div>

						<!-- 科目 -->
						<div class="col-3">
							<label class="form-label">
								科目
							</label>

							<select class="form-select" name="subjectId">

								<option value="">
									--------
								</option>

								<c:forEach var="subject" items="${subject_set}">

									<option value="${subject.id}">
										${subject.name}
									</option>

								</c:forEach>

							</select>
						</div>

						<!-- ボタン -->
						<div class="col-2">
							<button
								type="submit"
								class="btn btn-secondary w-100">

								検索

							</button>
						</div>

					</div>

				</form>

				<!-- =========================
				     学生情報検索
				========================= -->

				<form action="StudentScoreList.action" method="get">

					<div class="row align-items-end">

						<!-- ラベル -->
						<div class="col-2">
							<label class="form-label fw-bold">
								学生情報
							</label>
						</div>

						<!-- 学生番号 -->
						<div class="col-4">

							<label class="form-label">
								学生番号
							</label>

							<input
								type="text"
								name="studentNo"
								class="form-control".
								placeholder="学生番号を入力してください">

						</div>

						<!-- ボタン -->
						<div class="col-2">

							<button
								type="submit"
								class="btn btn-secondary w-100">

								検索

							</button>

						</div>

					</div>

				</form>

				<!-- メッセージ -->
				<div class="mt-4 text-info">

					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください

				</div>

				<!-- エラーメッセージ -->
				<c:if test="${not empty error}">

					<div class="mt-2 text-danger">
						${error}
					</div>

				</c:if>

			</div>

		</section>

	</c:param>

</c:import>