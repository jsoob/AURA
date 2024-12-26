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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

$(()=>{
	
	$("input[name='freeBNotice']").on("change", function () {
	    if ($(this).is(":checked")) {
	        $(this).val("1"); // 체크된 상태
	    } else {
	        $(this).val("0"); // 체크되지 않은 상태
	    }
	    console.log("Current value:", $(this).val());
	});
	
	// 임시저장
	$("#status").on("click",function(){
		
		console.log($("#title").val());
		console.log($(".summernote6").summernote('code'));
		console.log(${loginEmp.getEmpNo()});
		console.log($("input[name='freeBNotice']").val());
		console.log('-----------------------------');
		console.log($("input:checked[name='freeBPblc']").val());
		
		
		$.ajax({
	        url: "/aura/insertTempSave",
	        type: "get",
	        data: {
	            freeBTitle: $("#title").val(),
	            freeBContent: $(".summernote6").summernote('code'),
	            freeBCrtr : ${loginEmp.getEmpNo()},
	            freeBStatus: 0,
	            freeBNotice: $("input[name='freeBNotice']").val(),
	            freeBPblc:$("input:checked[name='freeBPblc']").val()
	        },
	        success: (response) => {
	            alert("임시저장 되었습니다.");
	            $(".summernote6").summernote('reset');		
				$("input[type=text]").val('');
	            // 성공적으로 저장된 후 임시저장 목록 갱신
	            loadSaveList();
	        }
	    });
	});
	
	$("#tempsaveBtn").on("click", ()=> {
		loadSaveList();
	});
	

	
})
	
	// 임시저장 불러오기
	function loadSaveList(){
		$.ajax({
	        url:"/aura/savelist", 
	        type: "get",
			dataType: 'json',
	        success: (data) => {
				$("tr[name='tempsaveList']").empty();
				$.each(data, function(idx, list) {
					let appendText = "";
					console.log(list.freeBNo);
					console.log(list.freeBTitle);
					console.log(list.freeBContent);
					console.log(list.freeBView);
					console.log(list.freeBNotice);
					console.log(list.freeBStatus);
					console.log(list.freeBPblc);
					console.log(list.freeBCrtr);
					console.log(list.createDate);
					console.log(list.updateDate);
						

					appendText = '<tr name="tempsaveList">';
					
					appendText += '<td><a onclick="getSaveList('+list.freeBNo+',\''+list.freeBTitle+'\',\''+list.freeBContent+'\')">'+list.freeBNo+'</td>';
					
					appendText += '<td><a onclick="getSaveList('+list.freeBNo+',\''+list.freeBTitle+'\',\''+list.freeBContent+'\')">'+list.freeBTitle+'</td>';
					appendText += '<td><a onclick="getSaveList('+list.freeBNo+',\''+list.freeBTitle+'\',\''+list.freeBContent+'\')">'+list.createDate+'</td>';
					appendText += '<td><a onclick="DeleteTempSave('+list.freeBNo+')"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>';
					
					appendText +="</tr>";
					
					$("#saveList").append(appendText);
				});
	        }
	    });
	}
	
	function getSaveList(no, title, content){
		
		$('input[name="freeBNo"]').val(no);
		
		$("#title").val(title);
		
		$(".summernote6").summernote('code', content);
				
		$('input[name="cmd"]').val("modifyFreeBOk");
		
		$("#tempsaveListModal").modal('hide');

	}
	
	function DeleteTempSave(no){
		if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			url:"/aura/deleteTempSave", 
	        type: "get",
	        data : {freeBNo : no},
	        success: (data) => {
	        	console.log(data);
	        	console.log("임시저장 글 삭제");
	        	alert("임시저장한 글이 삭제되었습니다.");
	        	loadSaveList();
	        	}
	        })
		}
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
					<form action="freeboard">

						<table class="table">
							<tr>
								<td colspan="2">
									<div class="col-sm-1">
										<label class="control-label text-left">제목:</label>
									</div>
									<div class="col-sm-11">
										<input type="text" name="freeBTitle" class="form-control" id="title" required/>
									</div>
								</td>
								<input type="hidden" name="cmd" value="writeFreeBOk" />
								<input type="hidden" name="freeBStatus" value="1">
								<input type="hidden" name="freeBNo" value="">

							</tr>
							<tr>
								<td colspan="2">
									<button type="button" class="btn" id="tempsaveBtn" data-toggle="modal" data-target=".tempsaveList-modal" >임시저장 목록</button>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea class="summernote6" name="freeBContent" id="" cols="50" rows="10"></textarea>
								</td>
							</tr>
							
							<tr>
								<!-- 로그인한 사람이 관리자라면 공지 등록 버튼이 보임 -->
								<c:if test="${loginEmp.getEmpNo() == 2024000}">
									<td colspan="2">공지로 등록
										<input type="checkbox" name="freeBNotice" value="0">
									</td>
								</c:if>
							</tr>
							<tr>
								<td colpsan="2">
									<input type="radio" name="freeBPblc" value="1" checked="checked" required> 공개
									<input type="radio" name="freeBPblc" value="0"> 비공개
								</td>
							</tr>

							<tr>
								<td colspan="2">
									<a href="freeboard?cmd=selectFreeB"><input type="button" class="btn btn-outline-primary" value="취소" /></a>
									<input type="button" class="btn btn-outline-success" id="status" name="status" value="임시저장">
									<input type="submit" class="btn btn-outline-success" value="등록" />
									<input type="button" class="btn btn-outline-danger" id="resetBtn" value="다시쓰기" />
								</td>
							</tr>
						</table>

					</form>
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
	
		<!-- 임시저장 목록 모달 -->
	<div class="modal fade tempsaveList-modal" id="tempsaveListModal"
		tabindex="-1" role="dialog" aria-labelledby="tempsaveListModalLabel">
		<!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
			<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="tempsaveListModalLabel">임시저장 목록</h4>
				</div>
				
				<div class="modal-body">
					<div class="product-status-wrap">
						<div class="asset-inner">
							<form action="admin">
								<table id="saveList">
									<tr>
										<th class="text-center col-sm-2">번호</th>
										<th class="text-center col-sm-5">제목</th>
										<th class="text-center col-sm-3">저장일시</th>
										<th class="text-center col-sm-1">삭제</th>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>
