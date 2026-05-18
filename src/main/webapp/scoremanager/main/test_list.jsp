<%-- 成績参照検索画面JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
	uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">

		得点管理システム

	</c:param>

	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">

				成績参照

			</h2>

			<div class="border rounded p-4">

				<!-- 科目検索 -->
				<form action="TestListSubjectExecute.action"
					method="post">

					<div class="row align-items-end mb-4">

						<div class="col-2">

							<label class="form-label fw-bold">

								科目情報

							</label>

						</div>

						<div class="col-2">

							<label class="form-label">

								入学年度

							</label>

							<select class="form-select"
								name="entYear">

								<option value="">

									--------

								</option>

								<c:forEach var="year"
									items="${ent_year_set}">

									<option value="${year}">

										${year}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-2">

							<label class="form-label">

								クラス

							</label>

							<select class="form-select"
								name="classNum">

								<option value="">

									--------

								</option>

								<c:forEach var="num"
									items="${class_num_set}">

									<option value="${num}">

										${num}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-3">

							<label class="form-label">

								科目

							</label>

							<select class="form-select"
								name="subject">

								<option value="">

									--------

								</option>

								<c:forEach var="subject"
									items="${subject_set}">

									<option value="${subject.cd}">

										${subject.name}

									</option>

								</c:forEach>

							</select>

						</div>

						<div class="col-2">

							<button type="submit"
								class="btn btn-secondary w-100">

								検索

							</button>

						</div>

					</div>

				</form>

				<!-- 学生検索 -->
				<form action="TestListStudentExecute.action"
					method="post">

					<div class="row align-items-end">

						<div class="col-2">

							<label class="form-label fw-bold">

								学生情報

							</label>

						</div>

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

						<div class="col-2">

							<button type="submit"
								class="btn btn-secondary w-100">

								検索

							</button>

						</div>

					</div>

				</form>

				<div class="mt-4 text-info">

					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください

				</div>

				<c:if test="${not empty error}">

					<div class="mt-2 text-danger">

						${error}

					</div>

				</c:if>

			</div>

			<!-- 学生検索結果 -->
			<c:if test="${mode == 'student'}">

				<hr class="mt-4">

				

				<div class="mb-2">

					氏名：
					${student.name}
					（${student.no}）

				</div>

				<c:choose>

					<c:when test="${list.size() > 0}">

						<table class="table table-hover">

							<tr>

								<th>科目名</th>
								<th>科目コード</th>
								<th>回数</th>
								<th>点数</th>

							</tr>

							<c:forEach var="score"
								items="${list}">

								<tr>

									<td>

										${score.subjectName}

									</td>

									<td>

										${score.subjectCd}

									</td>

									<td>

										${score.num}

									</td>

									<td>

										${score.point}

									</td>

								</tr>

							</c:forEach>

						</table>

					</c:when>

					<c:otherwise>

						<div>

							成績情報が存在しませんでした

						</div>

					</c:otherwise>

				</c:choose>

			</c:if>

			<!-- 科目検索結果 -->
			<c:if test="${mode == 'subject'}">

				<hr class="mt-5">

				<div class="mb-3">

					科目：
					${subject.name}

				</div>

				<c:choose>

					<c:when test="${list.size() > 0}">

						<table class="table table-hover">

							<tr>

								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>1回</th>
								<th>2回</th>

							</tr>

							<c:forEach
								var="score"
								items="${list}">

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

									<!-- 1回 -->
									<td>

										<c:choose>

											<c:when test="${score.point1 != null}">

												${score.point1}

											</c:when>

											<c:otherwise>

												-

											</c:otherwise>

										</c:choose>

									</td>

									<!-- 2回 -->
									<td>

										<c:choose>

											<c:when test="${score.point2 != null}">

												${score.point2}

											</c:when>

											<c:otherwise>

												-

											</c:otherwise>

										</c:choose>

									</td>

								</tr>

							</c:forEach>

						</table>

					</c:when>

					<c:otherwise>

						<div>

							学生情報が存在しませんでした

						</div>

					</c:otherwise>

				</c:choose>

			</c:if>

		</section>

	</c:param>

</c:import>