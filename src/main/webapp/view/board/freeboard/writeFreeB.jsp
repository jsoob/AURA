<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$("#status").on("click",()=>{
		alert("임시저장 되었습니다.");
		console.log("임시저장");
		$('input[name="freeBStatus"]').value = "0";
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
					<form action="freeboard">

						<table class="table">
							<tr>
								<td colspan="2">
									<div class="col-sm-1">
										<label class="control-label text-left">제목:</label>
									</div>
									<div class="col-sm-11">
										<input type="text" name="freeBTitle" class="form-control"
											id="" />
									</div>
								</td>
								<input type="hidden" name="cmd" value="writeFreeBOk" />
								<input type="hidden" name="freeBStatus" value="1">

							</tr>
							<tr>
								<td colspan="2"><textarea class="summernote6"
										name="freeBContent" id="" cols="50" rows="10"></textarea></td>
							</tr>
							<tr>
								<td colspan="2">공지로 등록 <input type="checkbox"
									name="freeBNotice" value="1">
								</td>
							</tr>
							<tr>
								<td colpsan="2"><input type="radio" name="freeBPblc"
									value="1" required> 공개 <input type="radio"
									name="freeBPblc" value="0"> 비공개</td>
							</tr>

							<tr>
								<td colspan="2"><a href="freeboard?cmd=selectFreeB"> <input
										type="button" class="btn btn-outline-primary" value="취소" /></a> <input
									type="button" class="btn btn-outline-success" id="status"
									name="status" value="임시저장"> <input type="submit"
									class="btn btn-outline-success" value="등록" /> <input
									type="button" class="btn btn-outline-danger" id="btn"
									value="다시쓰기" /></td>
							</tr>
						</table>

					</form>
				</div>
			</div>
		</div>
		<script>
				$("#btn").on("click", ()=>{
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