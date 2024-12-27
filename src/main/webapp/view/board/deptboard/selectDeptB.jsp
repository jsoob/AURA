<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>

<!-- FontAwesome 아이콘 로드 -->
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<style>
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }

    .page-item {
        list-style: none;
        margin: 0 5px;
    }

    .page-link {
        display: block;
        padding: 8px 12px;
        border: 1px solid #ddd;
        border-radius: 4px;
        text-decoration: none;
        color: #007bff;
    }

    .page-item.active .page-link {
        background-color: #007bff;
        color: #fff;
        border-color: #007bff;
    }
</style>
</head>
<body>
    <!-- Start Left menu area -->
    <jsp:include page="/view/comm/sidebar.jsp"></jsp:include>
    <!-- End Left menu area -->

    <!-- Start Welcome area -->
    <div class="all-content-wrapper">
        <jsp:include page="/view/comm/header.jsp"></jsp:include>

        <div class="container-area mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12">
                        <!-- 글쓰기 버튼 -->
                        <a href="deptboard?cmd=writeDeptBForm"> 
                            <input type="button" class="btn btn-outline-primary mg-bt-10" value="글쓰기" />
                        </a>

                        <!-- 글 목록 테이블 -->
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>게시판번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>등록일자</th>
                                    <th>조회수</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- 게시글 목록 출력 -->
                                <c:forEach var="vo" items="${list}">
                                    <tr>
                                        <td>${vo.deptBNo}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${vo.deptBPblc == 0}">
                                                   <!-- 비공개 표시 -->
                                                    <i class="fas fa-lock" style="color: gray; margin-right: 5px;"></i>
                                                </c:when>
                                            </c:choose>
                                            <!-- 공지사항 제목 앞에 "공지" 텍스트 추가 -->
                                            <c:if test="${vo.deptBNotice == 1}">
                                                <strong>[공지]</strong>
                                            </c:if>
                                            <a href="deptboard?cmd=detailDeptB&deptBNo=${vo.deptBNo}">
                                                ${vo.deptBTitle}
                                            </a>
                                        </td>
                                        <td>${vo.deptBCrtr}</td>
                                        <td>${vo.createDate}</td>
                                        <td>${vo.deptBView}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- 페이지네이션 -->
                        <nav>
                            <ul class="pagination">
                                <!-- 이전 버튼 -->
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="deptboard?cmd=selectDeptB&cp=${currentPage - 1}">이전</a>
                                    </li>
                                </c:if>

                                <!-- 페이지 번호 -->
                                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="deptboard?cmd=selectDeptB&cp=${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <!-- 다음 버튼 -->
                                <c:if test="${currentPage < totalPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="deptboard?cmd=selectDeptB&cp=${currentPage + 1}">다음</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/view/comm/footer.jsp"></jsp:include>
    </div>
    <jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>