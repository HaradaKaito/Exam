<%-- 科目別成績参照JSP --%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
	uri="jakarta.tags.core"%>

<div class="mt-5">

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

</div>