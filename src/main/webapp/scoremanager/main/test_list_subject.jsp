<%-- 科目別成績参照JSP --%>
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
				成績一覧（科目）
			</h2>

			<!-- =========================
			     検索フォーム
			========================= -->

			<div class="border rounded p-4 mb-4">

				<!-- 科目検索 -->
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

									<option
										value="${year}"
										<c:if test="${year == entYear}">
											selected
										</c:if>>

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

									<option
										value="${num}"
										<c:if test="${num == classNum}">
											selected
										</c:if>>

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

									<option
										value="${subject.id}"
										<c:if test="${subject.id == subjectId}">
											selected
										</c:if>>

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

				<!-- 学生検索 -->
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
								class="form-control"
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

			</div>

			<!-- =========================
			     一覧表示
			========================= -->

			<c:choose>

				<c:when test="${scores.size() > 0}">

					<!-- 科目名 -->
					<div class="mb-2">

						科目：${subjectName}

					</div>

					<!-- テーブル -->
					<table class="table table-hover">

						<tr>

							<th>
								入学年度
							</th>

							<th>
								クラス
							</th>

							<th>
								学生番号
							</th>

							<th>
								氏名
							</th>

							<th class="text-center">
								1回
							</th>

							<th class="text-center">
								2回
							</th>

						</tr>

						<c:forEach var="score" items="${scores}">

							<tr>

								<td>
									${score.entYear}
								</td>

								<td>
									${score.classNum}
								</td>

								<td>
									${score.studentNo}
								</td>

								<td>
									${score.studentName}
								</td>

								<td class="text-center">
									${score.point1}
								</td>

								<td class="text-center">
									${score.point2}
								</td>

							</tr>

						</c:forEach>

					</table>

				</c:when>

				<c:otherwise>

					<div class="text-muted">

						成績情報が存在しませんでした。

					</div>

				</c:otherwise>

			</c:choose>

		</section>

	</c:param>

</c:import>