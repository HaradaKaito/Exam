<%-- 科目情報削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%-- 共通のベースデザインを読み込み --%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>

            <div class="px-4">
                <%-- 削除処理を実行するActionへ送信 --%>
                <form action="SubjectDelete.action" method="post">
                    
                    <%-- 確認メッセージ --%>
                    <%-- 「」の中には選択された科目名と科目番号が表示される --%>
                    <p class="mt-3">
                        「${subject_name}(${subject_cd})」を削除してもよろしいですか
                    </p>

                    <%-- 削除対象を特定するための隠しフィールド --%>
                    <input type="hidden" name="cd" value="${subject_cd}">
                    <input type="hidden" name="name" value="${subject_name}">
	
                    <div class="mt-4">
    					<%-- ③ 削除ボタン：横幅いっぱいにしない場合は、そのまま配置 --%>
    					<div>
        					<button type="submit" class="btn btn-danger">削除</button>
    					</div>

    					<%-- ④ 戻るリンク：mt-3などで上のボタンとの間隔を空ける --%>
    					<div class="mt-3">
        					<a href="SubjectList.action">戻る</a>
    					</div>
					</div>
                </form>
            </div>
        </section>
    </c:param>
</c:import>