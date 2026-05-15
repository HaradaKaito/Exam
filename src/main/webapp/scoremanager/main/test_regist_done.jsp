<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/common/base.jsp">
 
    <c:param name="title">
        得点登録完了
    </c:param>
 
    <c:param name="content">
 
        <section class="me-4">
 
            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>
 
            <!-- 完了メッセージ -->
            <div class="alert alert-success" role="alert">
                登録が完了しました
            </div>
 
            <!-- リンク -->
            <div class="mt-4">
                <a href="TestRegist.action">戻る</a>
                &nbsp;&nbsp;
                <a href="TestList.action">成績参照</a>
            </div>
 
        </section>
 
    </c:param>
 
</c:import>
 
 