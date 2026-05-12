<%-- 科目情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    
    <c:param name="content">
        <section class="me-4">
            <%-- ① 画面タイトル --%>
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <form action="SubjectUpdateExecute.action" method="post" class="mt-4 px-4">
                
                <%-- ②③ 科目コード表示 --%>
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <%-- 画面表示用：設計書に合わせて枠線なしのプレーン表示 --%>
                    <div class="mb-1">${subject.cd}</div>
                    <%-- 送信用：readonly属性を付与したhidden的な役割のinput --%>
                    <input type="hidden" name="cd" value="${subject.cd}" />
                        
                    <%-- 設計書③：存在チェックエラーの表示（科目コードの下に表示） --%>
                    <c:if test="${not empty errors.get('not_found')}">
                        <div class="text-warning small mt-1">
                            ${errors.get("not_found")}
                        </div>
                    </c:if>
                </div>

                <%-- ④⑤ 科目名入力 --%>
                <div class="mb-3">
                    <label class="form-label" for="subject-name-input">科目名</label>
                    <%-- placeholderとmaxlengthを設計書通りに設定 --%>
                    <input class="form-control" type="text" id="subject-name-input" name="name" 
                        value="${subject.name}" placeholder="科目名を入力してください" maxlength="20" required />
                    
                    <%-- エラー表示エリア（設計書のオレンジ色に合わせる） --%>
                    <c:if test="${not empty errors.get('name')}">
                        <div class="text-warning small mt-1">${errors.get("name")}</div>
                    </c:if>
                </div>

                <div class="mt-4">
                    <%-- ⑥ 変更ボタン --%>
                    <div>
                        <button class="btn btn-primary" id="update-button">変更</button>
                    </div>

                    <%-- ⑦ 戻るリンク --%>
                    <div class="mt-3">
                        <a href="SubjectList.action">戻る</a>
                    </div>
                </div>
            </form>
        </section>
    </c:param>
</c:import>