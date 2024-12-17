<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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


		<div class="container-fluid">
			<a href="freeboard?cmd=WriteFreeB">
				<input type="button"class="btn btn-outline-primary" value="글쓰기" /></a>
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
						<td>${vo.freeBNo}</td>
						<td><a href="freeboard?cmd=DetailFreeB&freeBNo=${vo.freeBNo}"> ${vo.freeBTitle}</a></td>
						<td>${vo.freeBCrtr}</td>
						<td>${vo.createDate}</td>
						<td>${vo.freeBView}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>



</body>
</html>