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

#cmntText {
	height: 100%;
	width:100%;
}

.container-area .container-fluid {
    height: auto;
}
</style>
<script>

$(()=>{
	
	// 댓글목록 처음 실행
	loadComment();
	
	// 댓글달기 누르면 해당 내용이 디비에 저장되게 함
	$("#submitComment").on("click",()=>{
		const comment = $("#commentArea").val().trim(); 
		const postId = '${vo.freeBNo}';
		const userId = '${loginEmp.getEmpNo()}';
		if(comment == null){
			alert("내용을 입력하세요");
			
		}else {
			$.ajax({
				type:"get", // GET, POST
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
	                loadComment();
					}
			});
		}
	})
})



// 댓글 수정
	$(document).on("click" , ".modifyBtn", function(){
			//console.log("test");
			//console.log($(this));
			console.log($(this).parent().parent().parent().find(".comment-content").text());
			//let commentContent = $(this).closest('.panel-body').find('.comment-content').text();
			//console.log(commentContent);
			let content = $(this).parent().parent().parent().find(".comment-content").text();
			
			console.log($(this).parent().parent().parent().parent().children().children().find("span.txt").text());
			let no = $(this).parent().parent().parent().parent().children().children().find("span.txt").text();
			
			//console.log(data.dataset.commentCmntno);
			
		  /*   let cmntNo = $(this).data('cmntno');
		    let content = $(this).data('commentContent');
		    console.log(cmntNo);
		    console.log(content); */
			
		    
		    
		  let addhtml = '<input type="hidden" name="no" value="'+no+'" /> <textarea class="form-control" id="cmntText" maxlength="100" required>'+content+'</textarea><button class="saveBtn"> 저장 </button><button class="cancelBtn"> 취소 </button>';
			
		  $(this).closest('.panel').find('.panel-body').first().html(addhtml);
			
	
		})

		// 댓글 수정후 저장시
	$(document).on("click" , ".saveBtn", function(){
		let cmntNo = $(this).closest('.panel').find('input[name="no"]').val();

	    // textarea에서 수정된 내용 가져오기
	    let content = $(this).closest('.panel').find('#cmntText').val();

	    console.log("저장할 댓글 번호:", cmntNo);
	    console.log("수정된 내용:", content);
	    
	$.ajax({
		type:"get",
		url:"/aura/comment",
		data:{
			cmntNo: cmntNo,
			content: content,
			cmd: "modifyCmnt",
		},
		success: function(data){
			alert("댓글이 수정되었습니다.");
			loadComment();
		}
	})
})
	
	// 댓글 삭제
	$(document).on("click" , ".deleteBtn", function(){
		
		
		let cmntNo = $(this).parent().parent().parent().parent().children().children().find("span.txt").text();
		
		 if (confirm("정말 삭제하시겠습니까?")) {
 	$.ajax({
		type:"get",
		url:"/aura/comment",
		data:{
			cmntNo: cmntNo,
			cmd: "deleteCmnt",
		},
		success: function(data){
			alert("댓글이 삭제되었습니다.");
			loadComment();
		}
	}) 
	}
})
	
	// 댓글 목록 출력
		function loadComment(){
		    $.ajax({
		        type: "get",
		        url: "/aura/comment",
		        dataType:'json',
		        data: {
		            freeBNo: ${vo.freeBNo},
		            cmd: "selectCmnt",
		        },
		        success: function(data){
		            // 댓글 리스트 초기화
		            $('.commentList').html('');
	
		            let commentList = data;
		            
		            $('#totalCmnt').html("댓글(" + commentList.length+")");
		            
		            commentList.forEach((comment) => {
		            	if(comment.deptName == null){
		            		comment.deptName ='';
		            	}
		            	if(comment.posName == null){
		            		comment.posName='';
		            	}
		                let commentHtml = 
		                    '<div class="panel panel-default"> <div class="panel-heading"> <strong> NO\. <span class="txt">'+comment.cmntNo+'</span> / 작성자 : '+ comment.deptName + ' ' +comment.empName + ' ' +comment.posName +'</strong> <span class="text-muted pull-right">'+comment.createDate+'</span></div><div class="panel-body"><span class="comment-content">'+comment.content+'</span><div class="panel-body"><span class="text-muted pull-right">';
		                    
		                    // 본인이 쓴 댓글이거나 관리자일 경우 수정, 삭제 버튼 보이게
		                    if(${loginEmp.getEmpNo()} == comment.userId || ${loginEmp.getEmpNo()} == 2024000){
		                    commentHtml += '<button class="modifyBtn"  > 수정 </button> <button class="deleteBtn"> 삭제 </button>';
		                    commentHtml += '</span></div></div></div>';
		                    }
		                $('.commentList').append(commentHtml);
		            });
		        }
		    }); 
		}


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
						<div class="product-status-wrap aura_content" style="height: auto !important;">

							<table class="table">
								<tr>
									<th>작성자</th>
									<td>
									
									 ${vo.deptName} ${vo.empName} ${vo.posName}
									
									</td>
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
									<th colspan="6">첨부파일</th>
								</tr>
									<c:forEach var="fileVo" items="${FBfileList}">
									<tr><td></td><td colspan="5"><a href="/aura/downloadFBFile?no=${fileVo.fileNo}">${fileVo.fileName}</a></td></tr>
									</c:forEach>
								<tr>
									<th>내용</th>
									<td colspan="5" style="min-height: 200px; height: 200px;">${vo.freeBContent}</td>
								</tr>

								<tr>
									<td colspan="6"><a href="freeboard?cmd=selectFreeB"
										class="btn btn-outline-primary">목록</a>
										<c:if test="${loginEmp.getEmpNo() == vo.freeBCrtr || loginEmp.getEmpNo() == 2024000}">
											<a href="freeboard?cmd=modifyFreeB&freeBNo=${vo.freeBNo}" class="btn btn-outline-warning">수정</a>
											<a href="freeboard?cmd=deleteFreeB&freeBNo=${vo.freeBNo}" class="btn btn-outline-danger">삭제</a>
										</c:if>
									</td>
								</tr>

							</table>
							
							<span id="totalCmnt"></span>
							<button onclick="loadComment()" class="btn btn-link"><i class="fa fa-refresh" aria-hidden="true"></i></button>
							<hr />
							
							
							
							<label>작성자 : ${loginEmp.getEmpName()}</label>
							<textarea id="commentArea" placeholder="댓글을 입력하세요" required></textarea>
							<div class="text-right">
							    <input type="button" class="btn" value="댓글달기" id="submitComment" />
							</div>
							<hr>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"></div>
							
							<div class="commentList">
							
							<!-- 여기에 댓글이 추가되게 해야함 -->
							
							</div>
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