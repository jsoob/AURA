<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실(arc)의 글쓰기 안에 내용</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>


</head>
<body>
	<h2>이것또한 자료실 글쓰기안에 내용 원투원투</h2>
	
	<div class="container">
		<form action="board.do">
			<table class="table">
				<tr>
					<th>작성자</th>
					<!--
					고민. 작성자가 글쓰기 버튼을 클릭해서 접속했을 때
					작성자 : text를 줘서 직접 입력하게끔 하던가
					아니면 el로 작성자가 자동으로 불러와지게끔 하던가
					선택적인 사항이니 일단 text로 직접 입력하게끔 하고 먼저 설계하기					
					 -->
					<td>
						<input type="text" name="arcwrite" id="" />
						<a href="board.do?cmd="></a>	
					</td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="" ></td>
				</tr>
				
				<tr>
					<th>첨부파일</th>
					<td></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<!-- cols : 열(가로)크기 설정 -->
					<td><textarea name="contents" class="summernote" id="" cols="80" rows="10"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="2">
					
					<input type="button" class="btn btn-outline-primary" onclick="window.location.href='arclist.jsp';" value="목록" />
					<input type="submit" class="btn btn-outline-success" value="작성" /> 
					<input type="reset" class="btn btn-outline-danger" value="다시쓰기" /> 
					
					
					</td>
				</tr>			
				
				
					
				
				
				
				
			</table>
		</form>
	
	
	</div>
	

</body>
</html>