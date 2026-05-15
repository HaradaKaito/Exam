<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
 
    <c:param name="title">成績登録</c:param>
 
    <c:param name="content">
 
        <section class="me-4">
 
            <!-- タイトル -->
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>
 
            <!-- ============================
                 検索フォーム
            ============================ -->
            <form action="TestRegist.action" method="get">
                <div class="row border-bottom pb-3">
 
                    <!-- 入学年度 -->
                    <div class="col-2">
                        <label class="form-label" for="entYear">入学年度</label>
                        <select class="form-select" name="entYear" id="entYear">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == entYear}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- クラス -->
                    <div class="col-2">
                        <label class="form-label" for="classNum">クラス</label>
                        <select class="form-select" name="classNum" id="classNum">
                            <option value="">--------</option>
                            <c:forEach var="cNum" items="${class_num_set}">
                                <option value="${cNum}" <c:if test="${cNum == classNum}">selected</c:if>>
                                    ${cNum}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 科目 -->
                    <div class="col-4">
                        <label class="form-label" for="subjectId">科目</label>
                        <select class="form-select" name="subjectId" id="subjectId">
                            <option value="">--------</option>
                            <c:forEach var="sub" items="${subject_set}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == subjectId}">selected</c:if>>
                                    ${sub.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 回数 -->
                    <div class="col-2">
                        <label class="form-label" for="count">回数</label>
                        <select class="form-select" name="count" id="count">
                            <option value="">--------</option>
                            <c:forEach var="i" begin="1" end="2">
                                <option value="${i}" <c:if test="${i == count}">selected</c:if>>
                                    ${i}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 検索ボタン -->
                    <div class="col-2 d-flex align-items-end">
                        <button class="btn btn-secondary" id="search" name="search">検索</button>
                    </div>

                </div>
            </form>
 
            <!-- ============================
                 全体エラー
            ============================ -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">${error}</div>
            </c:if>
 
            <!-- ============================
                 成績入力フォーム
            ============================ -->
            <c:if test="${not empty students}">
 
                <form action="TestRegistExecute.action" method="post" class="mt-4">
 
                    <p>科目：${subjectName}（${count}回）</p>
 
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
 
                        <tbody>
                            <c:forEach var="test" items="${students}">
                                <tr>
                                    <td>${test.student.entYear}</td>
                                    <td>${test.student.classNum}</td>
 
                                    <td>
                                        ${test.student.no}
                                        <input type="hidden" name="studentNo" value="${test.student.no}">
                                    </td>
 
                                    <td>${test.student.name}</td>
 
                                    <td>
                                        <!-- 点数入力 -->
                                        <input type="text"
                                               name="point"
                                               value="${test.point}"
                                               class="form-control"
                                               style="width: 150px;">
 
                                        <!-- 行ごとのエラー表示 -->
                                        <c:if test="${not empty errors[test.student.no]}">
                                            <div class="text-warning small mt-1" style="color: #ffa500 !important;">
                                                <c:out value="${errors[test.student.no]}" />
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
 
                    <!-- hidden パラメータ -->
                    <input type="hidden" name="entYear" value="${entYear}">
                    <input type="hidden" name="classNum" value="${classNum}">
                    <input type="hidden" name="subjectId" value="${subjectId}">
                    <input type="hidden" name="count" value="${count}">
 
                    <button type="submit" class="btn btn-dark mt-3">登録して終了</button>
 
                </form>
 
            </c:if>
 
        </section>
 
    </c:param>
 
</c:import>
 
 