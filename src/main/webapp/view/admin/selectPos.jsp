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
			<a href="admin?cmd=insertPos"><input type="button" class="btn btn-outline-primary" value="직급등록"/></a>
			<table class="table" width="100px" height="100px">
				<tr>
					<th>직급번호</th>
					<th>직급명</th>
				</tr>
				<c:forEach var="vo" items="${list}">
					<tr>
					
						<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posNo}</a></td>
						<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posName}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>