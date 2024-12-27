<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>

<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<style>
.text-center {
    text-align: center !important;
}
</style>
<script>

	$(()=>{
		loadFreeBList();
		
		// 엔터를 눌러도 검색버튼을 누른 것처럼 작동
		$("#searchWord").on("keypress",function(e){
			// console.log("Search Word:", $("#searchWord").val());
			if(e.key == "Enter") {
				e.preventDefault(); // 기본동작 방지
				console.log("Search Word:", $("#searchWord").val());
				loadFreeBList();
			}
		});
	})
	
	// 게시글 목록
	function loadFreeBList (){
		
		let sendData = $("form[name=searchForm]").serialize();
		
		let cnt=0;
		
		$.ajax({
			type:"get",
			url : "/aura/freeboard2",
			dataType: 'json',
			data: sendData,
			success:function(data){
				$("#searchWord").val('');
				 $('.freeBList').empty();
				 
				 $('#total').empty();
				 // $('#total').append(data.length);
				 
				    // 데이터가 없을 경우 처리
				    if (data.length === 0) {
				        $(".freeBList").append("<tr><td colspan='5' class='text-center'>게시글이 없습니다.</td></tr>");
				    }
				 
				let obj = data;
                $.each(obj,(index, freeB)=>{
                	let notice;	// 공지
                	let lock;	// 자물쇠 
                	let rowHtml='';
                	
                	// 공지, 공개여부, 내가쓴 글인지
                	if(freeB.freeBNotice == 1) notice='[공지]';
                	else notice='';
                	
  					// 비공개인 게시글
                	if(freeB.freeBPblc==0){
                		lock='<i class="fa fa-lock" aria-hidden="true"></i>';
                	}
                	else {lock=''};
                	
                	console.log(${loginEmp.getEmpNo()});
                	
                	rowHtml += '<tr>';
                	// 관리자라면 임시저장 제외 다 보이게
                	if(${loginEmp.getEmpNo()} == '2024000'){
                		cnt++;
                		rowHtml += '<td>'+freeB.freeBNo+'</td><td>'+lock+notice+'<span id=lock></span>' +'<a href="freeboard?cmd=detailFreeB&freeBNo='+freeB.freeBNo+'">'+freeB.freeBTitle+'</a></td><td>'+freeB.freeBCrtr+'</td><td>'+freeB.createDate+'</td><td>'+freeB.freeBView+'</td>';
                	} else {
                		// 내가 쓴 글 
                		if(freeB.freeBCrtr == ${loginEmp.getEmpNo()}){
                			cnt++;
                			rowHtml += '<td>'+freeB.freeBNo+'</td><td>'+lock+notice+'<span id=lock></span>' +'<a href="freeboard?cmd=detailFreeB&freeBNo='+freeB.freeBNo+'">'+freeB.freeBTitle+'</a></td><td>'+freeB.freeBCrtr+'</td><td>'+freeB.createDate+'</td><td>'+freeB.freeBView+'</td>';
                		}else{
                			// 내가 쓴 글 아니고 공개인 게시글
                			if(freeB.freeBPblc == 1){
                				cnt++;
                				rowHtml += '<td>'+freeB.freeBNo+'</td><td>'+lock+notice+'<span id=lock></span>' +'<a href="freeboard?cmd=detailFreeB&freeBNo='+freeB.freeBNo+'">'+freeB.freeBTitle+'</a></td><td>'+freeB.freeBCrtr+'</td><td>'+freeB.createDate+'</td><td>'+freeB.freeBView+'</td>';
                			}
                		}
                	}
                	rowHtml += '</tr>';
                	
                	
                	$(".freeBList").append(rowHtml);
                	
                	
                	$('#total').empty();
                	$('#total').append(cnt);
                    
                	})      
			}
                	
		});	
		

   	 // 데이터가 없을 경우 처리
	    if (cnt === 0) {
	        $(".freeBList").empty();
	        $(".freeBList").append("<tr><td colspan='5' class='text-center'>게시글이 없습니다.</td></tr>");
	    }
		
	}

	// 정렬 // 이건 작동하지 않음
/* 	 $("#order").change(function(){
         if($(this).val() == "recent"){
             alert("최신순으로 정렬");
         } else if($(this).val() == "old"){
             alert("오래된순으로 정렬");
         } else if($(this).val() == "최철수"){
             alert("조회수순으로 정렬");
         }
     }); */
     
	// 옵션 선택하면 바로 정렬될 수 있게
	 $(document).on("change", "#order", function () {
		 // 선택한 옵션에 따라 다르게
		 if($(this).val() == "recent"){
             loadFreeBList();
         } else if($(this).val() == "old"){
             loadFreeBList();
         } else if($(this).val() == "view"){
             loadFreeBList();
         }
	});
	
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

							<!-- 검색부분 -->
							<div class="mg-bt-10">
								<div class="form-inline">
									<div class="pull-left">
									
										<label>전체글(<span id=total></span>)</label>
									
										<a href="freeboard?cmd=writeFreeBForm"> <input
											type="button" class="btn btn-outline-primary" value="글쓰기" />
										</a>
									</div>
									<div class="pull-right">
										
										<div class="form-group">
											
											<form name="searchForm">
												<select class="form-control" id="order" name="order">
									                <option value="recent">최신순</option>
									                <option value="old">오래된순</option>
									                <option value="view">조회순</option>
									            </select>
												
												<select name="search" class="form-control" id="search">
									                <option value="title">제목</option>
									                <option value="content">내용</option>
									                <option value="writer">작성자</option>
									            </select>
												
												<input type="text" class="form-control mg-wd-10"
													id="searchWord" name="searchWord" placeholder="검색어를 입력하세요">
												<span class="pd-lt-10">
													<button type="button" onclick="loadFreeBList()" class="btn pd-setting">검색</button>
												</span>
												<!-- <input type="hidden" name="cmd" value="selectFreeBAsync" /> -->
											</form>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped">
								<tr>
									<th>게시판번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>등록일자</th>
									<th>조회수</th>
								</tr>
								<tbody class="freeBList">
			<%-- 					<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.freeBNo}</td>
										<td><a
											href="freeboard?cmd=detailFreeB&freeBNo=${vo.freeBNo}">
												${vo.freeBTitle}</a></td>
										<td>${vo.freeBCrtr}</td>
										<td>${vo.createDate}</td>
										<td>${vo.freeBView}</td>
									</tr>
								</c:forEach> --%>
									</tbody>								
							</table>
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