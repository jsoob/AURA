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
		loadBtn();
		
		$("#loadBtn").on("click", ()=> {
			loadBtn();
		});
		$("input[name='qdYN']").change(function(){
			loadBtn();		
		});
	});
	
	function loadBtn(cp){
		let sendData = $("form[name=empForm]").serialize();
		
		cp = typeof cp !== "undefined" ? cp : "";
		if(cp != "" ) sendData += "&cp="+ cp;
		
		$.ajax({
            url:"adminasync", 
            type: "post",
			data: sendData, // json 방식으로 서블릿에 보낼 데이터
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
            success: (data) => {
            	let empList = data.empList;
				$("tr[name='empList']").remove(); // .empty();
				
				$.each(empList, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='empList'>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.deptName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.empNo +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.empName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'><img alt='사원이미지 없음' src='"+ ( (row.empImage == null || row.empImage == "null") ? "" : row.empImage )  +"'></a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.posName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ ( (row.empEmail == null || row.empEmail == "null") ? "" : row.empEmail ) +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.hiredate +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ 
								(row.quitdate == null || row.quitdate == "" || row.quitdate == "undefined" ? "근무중" : row.quitdate) +"</a></td>";
					
					// 이 부분 해야함			
					appendText += "<td class='text-center'>"
									+ "<a onclick='modifyEmp("+ row.empNo +");'>" 
									+	"<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='수정'>"
									+		"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a> ";
								
								appendText += "<a onclick='disableEmp("+ row.empNo+", \""+row.empName+"\");'>";
							
								if( (row.quitdate == null || row.quitdate == "" || row.quitdate == "undefined") ) {
									appendText += "<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='퇴사'>";
								} else {
									appendText += "<button disabled='disabled' data-toggle='tooltip' class='pd-setting-ed' data-original-title='퇴사'>";
								}
								appendText += "<i class='fa fa-user-circle' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a>"
								 +"</td>"
								+"</tr>";
					
					$("#selectTable").append(appendText);
				});
				
				let pageObject = data.pageObject;
				$("tr[name='empPages']").remove(); // .empty();
				
				let appendText = "";
				appendText = '<tr name="empPages">'
							+ '<td colspan="9" class="text-center">'
								+ '<ul class="pagination mg-nn">'
								+ '<li class="page-item"><a class="page-link" onclick="loadBtn('+ pageObject.prevCnt +')">Previous</a></li>';
								
								
					for(let i = pageObject.startPage; i <= pageObject.endPage; i++ ) {
						appendText += '<li class="page-item">'
									+ '<a class="page-link" onclick="loadBtn('+ i +')">'+i+'</a>' //  href="adminasync?cmd=selectEmp&cp='+i+'"
								   + '</li>';
					}	
				appendText += '<li class="page-item"><a class="page-link" onclick="loadBtn('+ pageObject.nextCnt +')">Next</a></li>' // href="adminasync?cmd=selectEmp&cp='+ pageObject.nextCnt +'"
							 	+"</ul>";
							 +"</td>"
						+"</tr>";
						
				$("#selectTable").append(appendText);
            },
            error:function(request, err) {
            	console.log("error");
            	// console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            },
            complete: function () {
            }
            
        });
		
	} // end loadBtn
	
	function modifyEmp(empNo) {
		let form = document.createElement('form');
	       
	    let obj = document.createElement('input');
	   	obj.setAttribute('type', 'hidden');
	  	obj.setAttribute('name', 'empNo');
	  	obj.setAttribute('value', empNo);
	  	form.appendChild(obj);
	  	
	  	form.setAttribute('method', 'post');
	  	form.setAttribute('action', 'admin?cmd=modifyEmp');
	    document.body.appendChild(form);
	    form.submit();
	}
	
	function detailEmp(empNo) {
		/* 
		let form = document.querySelector("form");
	    form.action = "admin?cmd=selectEmp";
	    form.method ="post";
	    form.submit();
	     */
		let form = document.createElement('form');
	       
	    let obj = document.createElement('input');
	   	obj.setAttribute('type', 'hidden');
	  	obj.setAttribute('name', 'empNo');
	  	obj.setAttribute('value', empNo);
	  	form.appendChild(obj);
	  	
	  	form.setAttribute('method', 'post');
	  	form.setAttribute('action', 'admin?cmd=detailEmp');
	    document.body.appendChild(form);
	    form.submit();
	}
	
	function disableEmp(empNo, empName) {
		Swal.fire({
			   title: empName+' 사원을 퇴사처리 하겠습니까?',
			   text: '다시 되돌릴 수 없습니다. 신중하세요.',
			   icon: 'error',
			   
			   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
			   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
			   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
			   confirmButtonText: '처리', // confirm 버튼 텍스트 지정
			   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
			   reverseButtons: false, // 버튼 순서 거꾸로
			}).then(result => {
			   // 만약 Promise리턴을 받으면,
			   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
					$.ajax({
			        	url:"adminasync", 
			            type: "post",
						data: { 
							"cmd" : "disableEmp", 
							"empNo" : empNo
						}, // json 방식으로 서블릿에 보낼 데이터
						dataType: 'json', // json 타입으로 풀어줌
			            success: (data) => {
			            	let status = data.deleteStatus; // data에 deleteStatus json객체 꺼내기
			            	if(status == 1) { // 1이면 퇴사완료
				            	Swal.fire('퇴사처리 완료되었습니다.', '', 'success');
			            	}
			            },
			            error:function(request, err) {
			            	console.log("error");
			            	// console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			            },
			            complete: function () { // 일단 완료되면 다시 조회
							loadBtn();
			            }
			        });
				   /* 
				   $.ajax({
			            url:"adminasync", 
			            type: "post",
						data: { "cmd", "disableEmp", "empNo" : empNo }, // json 방식으로 서블릿에 보낼 데이터
			            success: (data) => {
			            	console.log("data = ", data);
			            	Swal.fire('삭제가 완료되었습니다.', '', 'success');
							loadBtn();
			            }
				   });
			    */
			   }
			});
		/* 
		let form = document.createElement('form');
	    
	    let obj = document.createElement('input');
	   	obj.setAttribute('type', 'hidden');
	  	obj.setAttribute('name', 'empNo');
	  	obj.setAttribute('value', empNo);
	  	form.appendChild(obj);
	  	
	  	form.setAttribute('method', 'post');
	  	form.setAttribute('action', 'admin?cmd=disableEmp');
	    document.body.appendChild(form);
	    form.submit();
	     */
	}
	
	function old_loadBtn(){
		let sendData = $("form[name=empForm]").serialize();
		
		$.ajax({
            url:"adminasync", 
            type: "post",
			data: sendData, // json 방식으로 서블릿에 보낼 데이터
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
            success: (data) => {
            	let rows = data;
				$("tr[name='empList']").remove(); // .empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='empList'>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.deptName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.empNo +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.empName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'><img alt='사원이미지 없음' src='"+ ( (row.empImage == null || row.empImage == "null") ? "" : row.empImage )  +"'></a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.posName +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.empEmail +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ row.hiredate +"</a></td>";
					appendText +="<td><a onclick='detailEmp("+ row.empNo +")'>"+ 
								(row.quitdate == null || row.quitdate == "" || row.quitdate == "undefined" ? "근무중" : row.quitdate) +"</a></td>";
					
					// 이 부분 해야함			
					appendText += "<td class='text-center'>"
									+ "<a onclick='modifyEmp("+ row.empNo +");'>" 
									+	"<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='수정'>"
									+		"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a> ";
									
								appendText += "<a onclick='disableEmp("+ row.empNo+", \""+row.empName+"\");'>";
								
								if( (row.quitdate == null || row.quitdate == "" || row.quitdate == "undefined") ) {
									appendText += "<button data-toggle='tooltip' class='pd-setting-ed' data-original-title='퇴사'>";
								} else {
									appendText += "<button disabled='disabled' data-toggle='tooltip' class='pd-setting-ed' data-original-title='퇴사'>";
								}
								appendText += "<i class='fa fa-user-circle' aria-hidden='true'></i>"
									+	"</button>"
									+ "</a>"
								 +"</td>"
								+"</tr>";
					
					$("#selectTable").append(appendText);
				});
            },
            error:function(request, err) {
            	console.log("error");
            	// console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            },
            complete: function () {
            }
            
        });
		
	} // end loadBtn
	
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
                        <div class="product-status-wrap aura_content">
                            
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
                                	<thead>
	                                    <tr>
	                                        <th style="width: 7%; min-width: 65px;">부서명</th>
											<th style="width: 5%; min-width: 65px;">사원번호</th>
											<th style="width: 10%; min-width: 100px;">사원명</th>
											<th style="width: 8%; min-width: 100px;">사원 이미지</th>
											<th style="width: 5%; min-width: 65px;">직급</th>
											<th style="width: 10%; min-width: 100px;">외부이메일</th>
											<th style="width: 8%; min-width: 65px;">입사일자</th>
											<th style="width: 8%; min-width: 65px;">퇴사일자</th>
											<th style="width: 8%; min-width: 100px;" class="text-center">수정/퇴사처리</th> <!-- Setting -->
	                                    </tr>
                                    </thead>
                                    <%-- 
                                    <tbody>
	                                    <c:forEach var="vo" items="${empList}">
											<tr name="empList">
												<td><a onclick="detailEmp(${vo.empNo})">${vo.deptName}</a></td> 
												<td><a onclick="detailEmp(${vo.empNo})">${vo.empNo}</a></td>
												<td><a onclick="detailEmp(${vo.empNo})">${vo.empName}</a></td>
												<td><a onclick="detailEmp(${vo.empNo})"><img alt="사원이미지 없음" src="${vo.empImage}"></a></td>
												<td><a onclick="detailEmp(${vo.empNo})">${vo.posName}</a></td>
												<td><a onclick="detailEmp(${vo.empNo})">${vo.empEmail}</a></td>
												<td><a onclick="detailEmp(${vo.empNo})">${vo.hiredate}</a></td>
												<td><a onclick="detailEmp(${vo.empNo})">${( vo.quitdate == null || row.quitdate == "" ? "근무중" : vo.quitdate )}</a></td>
												
												<td class="text-center">
													<a onclick="modifyEmp(${vo.empNo});">
														<button data-toggle="tooltip" class="pd-setting-ed" data-original-title="수정">
															<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</button>
													</a>
													
													<a onclick="disableEmp(${vo.empNo}, '${vo.empName}');"> 
														<c:choose>
															<c:when test="${ vo.quitdate eq null || row.quitdate eq '' }">
																<button data-toggle="tooltip" class="pd-setting-ed" data-original-title="퇴사">
															</c:when>
															<c:otherwise>
																<button disabled="disabled" data-toggle="tooltip" class="pd-setting-ed" data-original-title="퇴사">
															</c:otherwise>
													</c:choose>
															<i class="fa fa-user-circle" aria-hidden="true"></i>
														</button>
													</a>
												</td>
												
											</tr>
										</c:forEach>
									</tbody>
										<tr name="empPages">
											<td colspan="9" class="text-center">
												<ul class="pagination mg-nn">
												    <li class="page-item"><a class="page-link" href="admin?cmd=selectEmp&cp=${page['prevCnt'] }">Previous</a></li>
													<!-- currentPage-1 -->
													<c:forEach var="i" begin="${page['startPage'] }" end="${page['endPage'] }" step="1">
														<li class="page-item">
															<a class="page-link" href="admin?cmd=selectEmp&cp=${i }">${i }</a>
														</li>
													</c:forEach>	
													<li class="page-item"><a class="page-link" href="admin?cmd=selectEmp&cp=${page['nextCnt'] }">Next</a></li>
													<!-- currentPage+1 -->
												 </ul>
											</td>
										</tr>
									 --%>
                                </table>
                            </div>
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