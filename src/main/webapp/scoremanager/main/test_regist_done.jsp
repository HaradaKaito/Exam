<%-- 成績登録完了JSP --%>
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

			<!-- 完了メッセージ -->
			<div class="alert alert-success">

				登録が完了しました

			</div>

			<!-- リンク -->
			<div class="mt-5">

				<a
					href="TestRegist.action"
					class="me-5">

					戻る

				</a>

				<a href="TestList.action">

					成績参照

				</a>

			</div>

		</section>

	</c:param>

</c:import>