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
			
			<form action="freeboard">
			<table class="table">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id="" /></td>
					<input type="hidden" name="cmd" value="writeOk"/>
					<!-- WriteAction ==> db insert 다시 목록으로 리다이렉트 -->
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="freeBTitle" id="" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="summernote" name="freeBContent" id="" cols="50" rows="10"></textarea></td>
				</tr>
				<tr>
					<th>공지</th>
					<td><input type="text" name="freeBNotice" id="" /></td>
				</tr>
				<tr>
					<th>임시저장</th>
					<td><input type="text" name="freeBStatus" id="" /></td>
				</tr>
				<tr>
					<th>공개</th>
					<td><input type="text" name="freeBPblc" id="" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="" name="freeBCrtr" id="" /></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="button"
						class="btn btn-outline-primary" value="목록" /> <input
						type="submit" class="btn btn-outline-success" value="작성" /> <input
						type="reset" class="btn btn-outline-danger" value="다시쓰기" /></td>
				</tr>
			</table>

		</form>
			
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJS.jsp"></jsp:include>

</body>
</html>