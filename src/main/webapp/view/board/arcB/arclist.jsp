<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실list.jsp</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 	crossorigin="anonymous"></script>

<style>
	th {
	
	}

</style>

</head>
<body>

	<div>
		<h3>아직 부트스트랩 훔쳐오기 안했는데욥</h3>
		<h2>자료실입니당</h2>

		<a href="board.do?cmd=arcwrite"> <input
			type="button" class="btn btn-outline-primary" value="글쓰기" /></a>

		<table class="table table-striped">
			<tr>
				<th>게시판번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<c:foreach var="vo" items="${list}">
				<tr>
					<td>${vo.arcNo}</td>
					<td><a
						href="board.do여기는 뭐?cmd=detail&ARC_NO=${vo.arcNo}">${vo.arcTitle}</a></td>
					<td>${vo.empNo}</td>
					<td>${vo.createDate}</td>
					<td>${vo.arcView}</td>
				</tr>
			</c:foreach>

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
</body>
</html>