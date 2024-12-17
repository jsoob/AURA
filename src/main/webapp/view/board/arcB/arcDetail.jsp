<%@page import="com.aura.www.action.board.archivesvo.ArchivesVO"%>
<%@page import="com.aura.www.action.board.archivesdao.ArchivesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원번호에 따른 제목을 불러오는 jsp파일</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

</head>
<body>
	<h3>테스트테스트 사원번호제목 원투쓰리뽀</h3>

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

</body>
</html>