<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.search-bar {
	display: flex;
	justify-content: space-between; /* 검색 영역과 글쓰기 버튼 양쪽 정렬 */
	align-items: center;
	margin-bottom: 15px;
}

.search-bar form {
	display: flex;
	gap: 10px; /* 검색 필드 간 간격 */
	align-items: center;
}

.search-bar input[type="text"] {
	width: 250px;
	padding: 5px;
}

/* 테이블 아래로 내리기 */
.table {
	margin-top: 20px; /* 간격 조정 */
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#searchButton").click(function () {
            loadDeptBoardList();
        });

        $("#searchWord").keypress(function (e) {
            if (e.key === "Enter") {
                e.preventDefault();
                loadDeptBoardList();
            }
        });

        $("#order").change(function () {
            loadDeptBoardList();
        });

        function loadDeptBoardList(page) {
            let sendData = $("form[name=searchForm]").serialize() + `&page=${page}`;

            console.log("보내는 데이터:", sendData);

            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/deptboard?cmd=searchDeptBoard", // 정확한 cmd 지정
                data: sendData,
                dataType: "json",
                success: function (response) {
                    console.log("AJAX 응답 데이터:", response); // 응답 데이터 확인

                    $(".deptBoardList").empty(); // 기존 데이터를 초기화

                    if (response.list.length === 0) {
                        $(".deptBoardList").append("<tr><td colspan='5'>검색 결과 없음</td></tr>");
                        return;
                    }

                    response.list.forEach(board => {
                        console.log("게시글 데이터:", board); // 각 게시글 데이터 확인
                        $(".deptBoardList").append(
                            "<tr>" +
                                "<td>" + board.deptBNo + "</td>" +
                                "<td>" + 
                                    (board.deptBPblc == 0 ? '<i class="fas fa-lock" style="color: gray; margin-right: 5px;"></i>' : '') +
                                    (board.deptBNotice == 1 ? '<strong>[공지]</strong>' : '') + 
                                    "<a href='deptboard?cmd=detailDeptB&deptBNo=" + board.deptBNo + "'>" + board.deptBTitle + "</a>" +
                                "</td>" +
                                "<td>" + board.empName + "</td>" + 
                                "<td>" + board.createDate + "</td>" + 
                                "<td>" + board.deptBView + "</td>" +
                            "</tr>"
                        );
                    });
                },
                error: function (xhr, status, error) {
                    console.error("AJAX 요청 오류:", error);
                    console.log("상태:", status);
                    console.log("응답 텍스트:", xhr.responseText);
                    alert("검색 중 오류가 발생했습니다.");
                }
            });
        }    
    });
</script>
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
						<!-- 검색 및 글쓰기 버튼 영역 -->
						<div class="search-bar">
							<a href="deptboard?cmd=writeDeptBForm"> 
								<input type="button" class="btn btn-outline-primary mg-bt-10" value="글쓰기" />
							</a>
							<form name="searchForm" class="form-inline">
								<select name="order" id="order" class="form-control">
									<option value="recent">최신순</option>
									<option value="old">오래된순</option>
									<option value="view">조회수순</option>
								</select> 
								<select name="search" id="search" class="form-control">
									<option value="title">제목</option>
									<option value="content">내용</option>
									<option value="writer">작성자</option>
								</select> 
								<input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어를 입력하세요">
								<button type="button" id="searchButton" class="btn btn-primary">검색</button>
							</form>
						</div>

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
							<tbody class="deptBoardList">
								<!-- 게시글 목록 출력 -->
								<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.deptBNo}</td>
										<td>
											<c:choose>
												<c:when test="${vo.deptBPblc == 0}">
													<i class="fas fa-lock" style="color: gray; margin-right: 5px;"></i>
												</c:when>
											</c:choose> 
											<c:if test="${vo.deptBNotice == 1}">
												<strong>[공지]</strong>
											</c:if> 
											<a href="deptboard?cmd=detailDeptB&deptBNo=${vo.deptBNo}">${vo.deptBTitle}</a>
										</td>
										<td>${vo.empName}</td>
										<td>${vo.createDate}</td>
										<td>${vo.deptBView}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- 페이지네이션 -->
						<nav>
							<ul class="pagination">
								<c:if test="${currentPage > 1}">
									<li class="page-item">
										<a class="page-link" href="deptboard?cmd=selectDeptB&cp=${currentPage - 1}">이전</a>
									</li>
								</c:if>
								<c:forEach begin="${startPage}" end="${endPage}" var="i">
									<li class="page-item ${currentPage == i ? 'active' : ''}">
										<a class="page-link" href="deptboard?cmd=selectDeptB&cp=${i}">${i}</a>
									</li>
								</c:forEach>
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