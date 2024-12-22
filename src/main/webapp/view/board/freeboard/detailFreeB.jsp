<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<style>
#commentArea {

        width: 100%;
        min-height: 50px;
        max-height: 300px;
        padding: 10px;
        font-size: 16px;
        line-height: 1.5;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
        box-sizing: border-box;

}
#comments {
	width: 100%;
        min-height: 50px;
        max-height: 300px;
        padding: 10px;
        font-size: 16px;
        line-height: 1.5;
        border: 1px solid #ccc;
        border-radius: 5px;
        resize: none;
        box-sizing: border-box;
}
</style>
<script>

$(()=>{
	
	window.setInterval(loadComment, 100);
	
	// 댓글달기 누르면 해당 내용이 디비에 저장되게 함
	$("#submitComment").on("click",()=>{
		const comment = $("#commentArea").val().trim(); 
		const postId = '${vo.freeBNo}';
		const userId = '${loginEmp.getEmpNo()}';

		$.ajax({
			type:"post", // GET, POST
			async:true, // 비동기화 true, 동기화 false
			url : "/aura/comment", // 찾아갈 url
			data:{
                postId: postId,
                userId: userId,
                comment: comment,
                cmd:"insertCmnt",
            },
			success:function(data){
				alert('댓글이 등록되었습니다!');
                $('#commentArea').val(''); // 입력창 초기화
               	
                // loadComment 잘되면 여기에 추가해야함
			}
			
		});
	})
	
	// 댓글 불러오기
	function loadComment(){
		$.ajax({
			type:"post",
			url : "/aura/comment",
			data:{
				freeBNo:${vo.freeBNo},
                cmd:"selectCmnt",
            },
			success:function(data){
				// 댓글 리스트 초기화
				$('.commentList').html('');

				let commentList = data;
				commentList.forEach((comment) => {
					 let commentHtml = "<div id='comments'>"+"<label>"+"작성자 : " + comment.userId+"</label><br>"+comment.createDate+"<br>"+comment.content;
					 $(".commentList").append(commentHtml);
				});
			}
		});	
	}
})


</script>

</head>
<body>
	<!-- Start Left menu area -->
	<jsp:include page="/view/comm/sidebar.jsp"></jsp:include>

	<!-- End Left menu area -->
	<!-- Start Welcome area -->
	<div class="all-content-wrapper">
		<jsp:include page="/view/comm/header.jsp"></jsp:include>

		<div class="container-area mg-b-15">
			<div class="container-fluid">
				<div class="row">

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="product-status-wrap aura_content">

							<table class="table">
								<tr>
									<th>작성자</th>
									<td>${vo.freeBCrtr}</td>

									<th>작성일시</th>
									<td>${vo.createDate}</td>

									<th>조회수</th>
									<td>${vo.freeBView}</td>
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
									<td colspan="6"><a href="freeboard?cmd=selectFreeB"
										class="btn btn-outline-primary">목록</a>
										<c:if test="${loginEmp.getEmpNo() == vo.freeBCrtr}">
											<a href="freeboard?cmd=modifyFreeB&freeBNo=${vo.freeBNo}" class="btn btn-outline-warning">수정</a>
											<a href="freeboard?cmd=deleteFreeB&freeBNo=${vo.freeBNo}" class="btn btn-outline-danger">삭제</a>
										</c:if>
									</td>
								</tr>

							</table>
							
							<hr />
							<label>작성자 : ${loginEmp.getEmpNo()}</label>
							<textarea id="commentArea" placeholder="댓글을 입력하세요"></textarea>
							<div class="text-right">
							    <input type="button" class="btn" value="댓글달기" id="submitComment" />
							</div>
							
							
							<div class="commentList">
							
							<!-- 여기에 댓글이 추가되게 해야함 -->
							
						</div>
					</div>
					
				</div>
				</div>
			</div>
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
		
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>