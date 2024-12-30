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
.container-area .container-fluid {
    height: auto;
}
</style>
<script>
$(()=>{
	$(".delete-file").on("click",function(){
		const fileNo = $(this).closest("tr").find(".fileNo").text().trim();
		//console.log($(this).closest("tr").find(".fileNo").text().trim());
		const $row = $(this).closest("tr");
		
		if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			type:"get", // GET, POST
			async:true, // 비동기화 true, 동기화 false
			url : "/aura/deleteFBFile", // 찾아갈 url
			data:{
				no: fileNo,
            },
			success:function(data){
				$row.remove();
				alert('파일이 삭제되었습니다!');
				}
		});
		}
	})
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
					<form action="freeboard" method="post" enctype="multipart/form-data">
						<table class="table">
							<tr>
								<td colspan="2">
									<div class="col-sm-1">
										<label class="control-label text-left">제목:</label>
									</div>
									<div class="col-sm-11">
										<input type="text" name="freeBTitle" class="form-control"
											id="" value="${vo.freeBTitle}" required/>

									</div>
								</td>
								<input type="hidden" name="freeBNo" value="${vo.freeBNo}" />
								<input type="hidden" name="cmd" value="modifyFreeBOk" />
								<input type="hidden" name="file" value="insert">

							</tr>
							<tr>
								<th colspan="2">첨부파일</th>
							</tr>
								<c:forEach var="fileVo" items="${FBfileList}">
								<tr><td colspan="5"><span class="fileNo">${fileVo.fileNo}</span> ${fileVo.fileName} <button type="button" class="btn btn-link delete-file"><i class="fa fa-trash-o" aria-hidden="true"></i></button></td></tr>
								</c:forEach>
								
							<tr>
								<td colspan="2"><input type="file" name="filename" id="" multiple="multiple" /></td>
							</tr>
							<tr>
								<td colspan="2"><textarea class="summernote6" id=summernote
										name="freeBContent" id="" cols="50" rows="10">${vo.freeBContent}</textarea></td>
							</tr>
							<tr>
								<td colspan="2">공지로 등록 <input type="checkbox"
									name="freeBNotice" value="1">
								</td>
							</tr>
							<tr>
								<td colpsan="2"><input type="radio" name="freeBPblc"
									value="1" checked="checked" required > 공개 <input type="radio" name="freeBPblc"
									value="0"> 비공개</td>
							</tr>

							<tr>
								<td colspan="2"><a href="freeboard?cmd=selectFreeB"> <input
										type="button" class="btn btn-outline-primary" value="취소" /></a> <input
									type="submit" class="btn btn-outline-success" value="등록" /> <input
									type="button" class="btn btn-outline-danger" id="resetBtn"
									value="다시쓰기" /></td>
							</tr>
						</table>

					</form>

				</div>
			</div>
		</div>
		</div>
		</div>
				<script>
				// 다시 쓰기 누르면 reset
				$("#resetBtn").on("click", ()=>{
					$(".summernote6").summernote('reset');		
					$("input[type=text]").val('');
				/* 	document.getElementById("summernote6").value='';
					form태그객체.reset(); */
				});
		</script>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>


	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>