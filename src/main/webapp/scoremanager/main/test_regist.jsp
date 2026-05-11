<%-- 成績登録JSP --%>
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
				成績管理
			</h2>

			<!-- =========================
			     検索フォーム
			========================= -->

			<form action="TestRegist.action" method="get">

				<div class="border rounded p-4 mb-4">

					<div class="row align-items-end">

						<!-- 入学年度 -->
						<div class="col-2">

							<label class="form-label">
								入学年度
							</label>

							<select
								class="form-select"
								name="entYear">

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

							<select
								class="form-select"
								name="classNum">

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
						<div class="col-4">

							<label class="form-label">
								科目
							</label>

							<select
								class="form-select"
								name="subjectId">

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

						<!-- 回数 -->
						<div class="col-2">

							<label class="form-label">
								回数
							</label>

							<select
								class="form-select"
								name="count">

								<option value="">
									--------
								</option>

								<option
									value="1"
									<c:if test="${count == 1}">
										selected
									</c:if>>

									1

								</option>

								<option
									value="2"
									<c:if test="${count == 2}">
										selected
									</c:if>>

									2

								</option>

							</select>

						</div>

						<!-- 検索 -->
						<div class="col-2">

							<button
								type="submit"
								class="btn btn-secondary w-100">

								検索

							</button>

						</div>

					</div>

				</div>

			</form>

			<!-- =========================
			     検索後表示
			========================= -->

			<c:if test="${students.size() > 0}">

				<!-- 科目名 -->
				<div class="mb-3">

					科目：${subjectName}
					（${count}回）

				</div>

				<!-- 登録フォーム -->
				<form action="TestRegistExecute.action" method="post">

					<!-- hidden -->
					<input
						type="hidden"
						name="subjectId"
						value="${subjectId}">

					<input
						type="hidden"
						name="count"
						value="${count}">

					<table class="table table-hover align-middle">

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

							<th>
								点数
							</th>

						</tr>

						<c:forEach
							var="student"
							items="${students}"
							varStatus="status">

							<tr>

								<td>
									${student.entYear}
								</td>

								<td>
									${student.classNum}
								</td>

								<td>

									${student.no}

									<input
										type="hidden"
										name="studentNo"
										value="${student.no}">

								</td>

								<td>
									${student.name}
								</td>

								<td>

									<input
										type="text"
										name="point"
										class="form-control"
										value="${student.point}">

								</td>

							</tr>

						</c:forEach>

					</table>

					<!-- 登録 -->
					<button
						type="submit"
						class="btn btn-secondary mt-3">

						登録して終了

					</button>

				</form>

			</c:if>

			<!-- エラー -->
			<c:if test="${not empty error}">

				<div class="mt-3 text-danger">

					${error}

				</div>

			</c:if>

		</section>

	</c:param>

</c:import>