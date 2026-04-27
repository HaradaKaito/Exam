<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

            <form action="SubjectCreateExecute.action" method="post">
                <div class="px-4">
                    <%-- 科目コード入力エリア --%>
                    <div class="mb-3">
                        <label for="subject-code-input" class="form-label">科目コード</label>
                        <%-- 設計書No.3: 入力値保持のため value="${cd}" を設定 --%>
                        <input type="text" class="form-control" id="subject-code-input" name="cd" value="${cd}" maxlength="3" required placeholder="科目コードを入力してください">
                        
                        <%-- 
                           設計書No.3 備考: エラーメッセージの表示
                           Actionクラスから渡されたerrorsマップに"cd"キーがあれば表示する
                        --%>
                        <c:if test="${not empty errors.get('cd')}">
                            <div class="text-danger small mt-1">
                                ${errors.get('cd')}
                            </div>
                        </c:if>
                    </div>

                    <%-- 科目名入力エリア --%>
                    <div class="mb-3">
                        <label for="subject-name-input" class="form-label">科目名</label>
                        <%-- 設計書No.5: 入力値保持のため value="${name}" を設定 --%>
                        <input type="text" class="form-control" id="subject-name-input" name="name" value="${name}" maxlength="20" required placeholder="科目名を入力してください">
                        
                        <%-- 科目名のエラー用（必要に応じて） --%>
                        <c:if test="${not empty errors.get('name')}">
                            <div class="text-danger small mt-1">
                                ${errors.get('name')}
                            </div>
                        </c:if>
                    </div>

                    <%-- ボタンエリア --%>
                    <div class="mt-4">
                        <button type="submit" class="btn btn-primary">登録</button>
                    </div>
                    <div class="mt-2">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>