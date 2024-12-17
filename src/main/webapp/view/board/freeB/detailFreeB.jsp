<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


		<div class="container">
			<table class="table">
				<tr>
					<th>작성자</th>
					<td>${vo.freeBCrtr}</td>

					<th>작성일시</th>
					<td ${vo.createDate}></td>
					
					<th>조회수</th>
					<td ${vo.freeBView}></td>
				</tr>

				<tr>
					<th>제목</th>
					<td colspan="5">${vo.freeBTitle}</td>
				</tr>

				<tr>
					<th>내용</th>
					<td colspan="5">${vo.freeBContent}</td>
				</tr>

				<tr>
					<td colspan="6"><a href="freeboard?cmd=SelectFreeB"
						class="btn btn-outline-primary">목록</a> <a
						href="freeboard?cmd=ModifyFreeB&freeBNo=${vo.freeBNo}"
						class="btn btn-outline-warning">수정</a> <a
						href="freeboard?cmd=DeleteFreeB&freeBNo=${vo.freeBNo}"
						class="btn btn-outline-danger">삭제</a></td>
				</tr>

			</table>
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJS.jsp"></jsp:include>

</body>
</html>