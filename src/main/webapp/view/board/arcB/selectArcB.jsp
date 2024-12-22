<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 조회</title>

	<!-- header 영역에서 첨부된 css 파일+js -->
	<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

</head>
<body>
	<!-- Start Left menu area -->
	<jsp:include page="/view/comm/sidebar.jsp"></jsp:include>

	<!-- End Left menu area -->
	<!-- Start Welcome area -->
	<div class="all-content-wrapper">
		<jsp:include page="/view/comm/header.jsp"></jsp:include>


	<!-- 여기서부터 시작 -->
	
		<div>
			<a href="board.do?cmd=arcwrite">
				<input type="button" class="btn btn-outline-primary" value="글쓰기" /></a>
			<table class="table table-striped">
				<tr>
					<th>게시판번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일자</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.arcNo}</td>
						<td><a href="board.do여기는 뭐?cmd=detail&ARC_NO=${vo.arcNo}">${vo.arcTitle}</a></td>
						<td>${vo.empNo}</td>
						<td>${vo.createDate}</td>
						<td>${vo.arcView}</td>
					</tr>
				</c:forEach>
				
			<%-- 
		
				<용어정리>
			
				nav : 태그안에 있는 네비게이션 영역
				ul : (목록) 페이지 영역을 나눈다
				class="pagination" : 부트스트랩의 css를 사용 할 때 페이지네이션 스타일 적용하는 클래스
				<c:forEach> : JSP에서 사용하는 반복문의 태그
				var = "i" : i라는 변수를 기준으로 페이지 번호를 표시
				begin="${startPage}", end="${endPage}" : 시작 페이지와 끝 페이지
														 범위 안의 숫자를 순서대로 출력
				href="board.do?cp=${i}" : 페이지 번호에 맞는 링크 부여										 
				${i} : 현재 페이지 화면
		
		
		
			 --%>
	 
	 		<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="board.do?cp=${currendPage-1}">Previous</a></li>
								<c:foreach var="i" begin="${startPage}" end="${endPage}">
									<li class="page-item"><a class="page-link" href="board.do?cp=${i}">${i}</a></li>
								</c:foreach>
									<li class="page-item"><a class="page-link" href="board.do?cp=${currendPage+1}">Next</a></li>
						</ul>
					</nav>
				</td>
			</tr>
	 	
			</table>
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>



</body>
</html>