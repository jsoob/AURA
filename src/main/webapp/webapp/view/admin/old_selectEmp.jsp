<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<script type="text/javascript">
	$( ()=> {
		
		
		$("#loadBtn").on("click", ()=> {
			loadBtn();
		});
		$("input[name='qdYN']").change(function(){
			loadBtn();		
		});
	});
	
	function loadBtn(){
		console.log("loadBtn");
		let sendData = $("form[name=empForm]").serialize();
		
		$.ajax({
            url:"adminasync", // AAdminController.java로 접근
            type: "post",
			data: sendData, // json 방식으로 서블릿에 보낼 데이터
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
            success: (data) => {
            	let rows = data;
				$("tr[name='empList']").empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='empList'>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.deptName +"</a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.empNo +"</a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.empName +"</a></td>";
					appendText +="<td><a href='admin?cmd=modifyEmp&empNo="+ row.empNo +"'> <img alt='사원이미지 없음' src='"+ row.empImage +"'></a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.posName +"</a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.empEmail +"</a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ row.hiredate +"</a></td>";
					appendText +="<td><a href='admin?cmd=detailEmp&empNo="+ row.empNo +"'>"+ 
								(row.quitdate == null || row.quitdate == "" || row.quitdate == "undefined" ? "근무중" : row.quitdate) +"</a></td>";
					
								<a onclick="modifyEmp('2024001');">			
								
					// 이 부분 해야함			
					appendText += "<td class='text-center'>"
									+ "<a onclick='modifyEmp("+ row.empNo +");'>" 
									+ "<a href='admin?cmd=modifyEmp&empNo="+ row.empNo +"'>"
									+	"<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='수정'>"
									+		"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a>"
									+ "<a href='admin?cmd=deleteEmp&empNo="+ row.empNo +"'>"
									+	"<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='삭제'>"
									+		"<i class='fa fa-trash-o' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a>"
								 +"</td>";
								
					appendText +="</tr>";
					
					$("#selectTable").append(appendText);
				});
            },
            error:function(request, err) {
            	console.log("error");
            	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            },
            complete: function () {
            }
            
        });
		/* 
		let form = document.querySelector("form");
        form.action = "admin?cmd=selectEmp";
        form.method ="post";
        form.submit();
         */
	}
</script>

<%--
<script type="text/javascript">
	$(function() {
		fnSetDate();
	  
		$('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
			$(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
		});
	
		$('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
			$(this).val('');
		});
	});
	function fnSetDate() {
		$('input[name="hiredate"]').daterangepicker({
			autoUpdateInput: false,
			locale: {
				cancelLabel: 'Clear'
			}
		});
	}
</script>
 --%>
</head>
<body>
	<%-- Start Left menu area --%>
    <jsp:include page="/view/comm/sidebar.jsp"></jsp:include>
    
    <%-- End Left menu area --%>
    <%-- Start Welcome area --%>
	<div class="all-content-wrapper">
        <jsp:include page="/view/comm/header.jsp"></jsp:include>
        
        <div class="container-area mg-b-15">
            <div class="container-fluid">
                <!-- 여기부터 개별 -->
                <div class="row">
                    
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                            
                            <%-- 검색 부분 --%>
                           <form name="empForm">
	                           	 <div class="text-right mg-bt-10">
	                            	<div class="form-inline">
									  	  <div class="form-group">
									    	<label for="deptName">부서명</label>
									    	<input type="hidden" name="cmd" value="selectEmp" />
									    	<input type="text" class="form-control wd-100 mg-wd-10" id="deptName" name="deptName" placeholder="">
										  </div>
										  <div class="form-group">
										    <label for="empNo">사원번호</label>
										    <input type="email" class="form-control wd-80 mg-wd-10" id="empNo" name="empNo" placeholder="">
										  </div>
										  <div class="form-group">
										    <label for="empName">사원명</label>
										    <input type="email" class="form-control wd-100 mg-wd-10" id="empName" name="empName" placeholder="">
										  </div>
									  		
										  <div class="form-group">
										  	<label>입사일자</label>
	                                        <%-- <input type="text" name="hiredate" value="" /> --%>
	                                        <input type="date" name="hiredate_st" class="form-control mg-wd-10" required pattern="\d{4}-\d{2}-\d{2}" />
	                                        to
	                                        <input type="date" name="hiredate_ed" class="form-control mg-wd-10" required pattern="\d{4}-\d{2}-\d{2}" />
										  </div>
										  
										  <div class="form-group">
										  	<label class="radio-inline">
											  <!-- <input type="checkbox" id="qdYN1" name="qdYN" value="Y"> 퇴사 -->
											  <input type="radio" name="qdYN" id="qdYN1" value="ALL" checked="checked"> 전체
											</label>
											<label class="radio-inline">
											  <input type="radio" name="qdYN" id="qdYN2" value="N"> 근무중
											</label>
											<label class="radio-inline">
											  <input type="radio" name="qdYN" id="qdYN3" value="Y"> 퇴사
											</label>
										  </div>
										  
										  
									  		
									  	<span class="pd-lt-10">
									  		<button type="button" id="loadBtn" class="btn pd-setting">조회</button><!-- btn-primary -> pd-setting -->
									  	</span>
									  	
									  	<span class="pd-lt-10">
									  		<a href="admin?cmd=insertEmp" class="btn pd-setting">사원 등록</a>
										</span>
										
									  </div>
	                            </div>
                           </form>
                            
                            <%-- 테이블 부분 --%>
                            <div class="asset-inner">
                                <table id="selectTable">
                                	<%-- 테이블 컬럼 --%>
                                    <tr>
                                        <th class="">부서명</th>
										<th class="">사원번호</th>
										<th class="">사원명</th>
										<th class="">사원 이미지</th>
										<th class="">직급</th>
										<th class="">외부이메일</th>
										<th class="">입사일자</th>
										<th class="">퇴사일자</th>
										<th class="text-center">Setting</th>
                                    </tr>
                                    <!-- <td><img src="img/product/book-1.jpg" alt=""></td> -->
                                	<%-- 테이블 값 --%>
                                    <c:forEach var="vo" items="${empList}">
										<tr name="empList">
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.deptName}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.empNo}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.empName}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}"><img alt="사원이미지 없음" src="${vo.empImage}"></a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.posName}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.empEmail}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${vo.hiredate}</a></td>
											<td class=""><a href="admin?cmd=detailEmp&empNo=${vo.empNo}">${( vo.quitdate == null || row.quitdate == "" ? "근무중" : vo.quitdate )}</a></td>
											
											<td class="text-center">
												<a onclick="modifyEmp('${vo.empNo}');"> <%-- href="admin?cmd=modifyEmp&empNo=${vo.empNo}" --%>
													<button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="수정">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
												</a>
												<a onclick="deleteEmp('${vo.empNo}');"> <%-- href="admin?cmd=deleteEmp&empNo=${vo.empNo}" --%>
													<button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="삭제">
														<i class="fa fa-trash-o" aria-hidden="true"></i>
													</button>
												</a>
											</td>
											
										</tr>
									</c:forEach>
                                </table>
                            </div>
                            <%-- 테이블 페이징 처리 --%>
                            <!-- 
                            <div class="custom-pagination">
								<ul class="pagination">
									<li class="page-item"><a class="page-link" href="#">Previous</a></li>
									<li class="page-item"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#">2</a></li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">Next</a></li>
								</ul>
                            </div>
                             -->
                        </div>
                    </div>
                    
                </div>
                
                <!-- 이전 -->
                <%-- 
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<a href="admin?cmd=insertPos">
						<input type="button" class="btn btn-outline-primary" value="직급등록" />
					</a>
					<table class="table" width="100px" height="100px">
						<tr>
							<th>부서명</th>
							<th>사원번호</th>
							<th>사원명</th>
							<th>직급</th>
							<th>외부이메일</th>
							<th>입사일자</th>
						</tr>
						<c:forEach var="vo" items="${list}">>
							<tr>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.deptName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empNo}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empEmail}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.hiredate}</a></td>
							</tr>
						</c:forEach>
					</table>    		
                </div>
                 --%>
            </div>
        </div>
        
        <jsp:include page="/view/comm/footer.jsp"></jsp:include>
    </div>
	
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>