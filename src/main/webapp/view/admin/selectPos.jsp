<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>직급번호</th>
			<th>직급명</th>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>${posNo}</td>
			<td>${posName}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>