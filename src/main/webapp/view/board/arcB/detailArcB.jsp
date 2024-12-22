<%@page import="com.aura.www.vo.archives.ArchivesVO"%>
<%@page import="com.aura.www.dao.archives.ArchivesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detailArcB.jsp</title>

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


	<!-- 여기에서부터 시작 -->
	
	<%
	
		// 1. 파라미터 값 가져오기
		String b = request.getParameter("arcNo");
		// 만약 arcNo에서 받아온 b의 변수가 null이 아니라면?
		if(b != null){
			
			// 2. dao를 불러오기
			ArchivesDAO dao = new ArchivesDAO();
			// String이 아닌 int로 변환하기 (형변환)
			int arcNo = Integer.parseInt(b);
			
			// 5. 조회수 증가 (raiseHits() 사용)
			dao.arcView(arcNo);
			
			// 3. dao.selectOne(int arcNo);
			ArchivesVO vo = dao.selectOne(arcNo);
			
			if (vo != null){
				
			// 4. vo로부터 제목, 작성자, 내용을 콘솔에 출력할 수 있게 테스트 하기
			System.out.println("제목 : " + vo.getArcTitle());
			System.out.println("작성자 : " + vo.getEmpNo());
			System.out.println("내용 : " + vo.getArcContent());

	%>


<div>
		<table>
			<tr>
				<th>작성자</th>
				<td>${vo.empNo}</td>
				<th>날짜</th>
				<td>${vo.createDate}</td>
				<th>조회수</th>
				<td>${vo.arcView}</td>
			</tr>
				
			<tr>
				<th>제목</th>
				<td colspan="5">${vo.arcTitle}</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td colspan="5">${vo.arcContent}</td>
			</tr>
			
			<tr>
				<td colspan="6">
					<a href="board.do?cmd=list" class="btn btn-outline-primary">목록</a> 
					<a href="board.do?cmd=modify&arcNo=${vo.arcNo}" class="btn btn-outline-primary">수정</a> 
					<a href="board.do?cmd=delete&arcNo=${vo.arcNo}" class="btn btn-outline-primary">삭제</a></td>

			</tr>	
		</table>
	</div>


	<%
			}
		}
	%>


		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>