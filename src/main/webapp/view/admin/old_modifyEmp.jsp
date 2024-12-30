<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

<script type="text/javascript">
	$( ()=> {
		let file;
		
		$("#resetPw").on("click", ()=> {
			resetPw();
		});
		
		$("#deptAdd").on("click", ()=> {
			loadDept();
		});
		
		$("#posAdd").on("click", ()=> {
			loadPos();
		});
		
		$("#delImg").on("click", ()=> {
			removeImage();
		});
		
		$("#modifyEmpOk").on("click", ()=> {
			modifyEmpOk();
		});
	});
	
	function resetPw() {
		Swal.fire({
		   title: '비밀번호를 초기화 하겠습니까?',
		   text: '다시 되돌릴 수 없습니다. (PW초기화 : 사원번호)',
		   icon: 'error',
		   
		   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
		   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
		   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
		   confirmButtonText: '초기화', // confirm 버튼 텍스트 지정
		   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		   reverseButtons: false, // 버튼 순서 거꾸로
		}).then(result => {
		   // 만약 Promise리턴을 받으면,
		   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
				$.ajax({
		        	url:"adminasync", 
		            type: "post",
					data: { 
						"cmd" : "resetPwEmp", 
						"empNo" : ${empVo.empNo}
					}, // json 방식으로 서블릿에 보낼 데이터
					dataType: 'json', // json 타입으로 풀어줌
		            success: (data) => {
		            	let status = data.resetPwStatus; // data에 resetPwStatus json객체 꺼내기
		            	if(status == 1) { // 1이면 퇴사완료
			            	Swal.fire('비밀번호 초기화가 완료되었습니다.', '', 'success');
		            	}
		            },
		            error:function(request, err) {
		            	console.log("error");
		            }
		        });
		   }
		});
	}

	function loadDept(){
		$.ajax({
	        url:"adminasync", 
	        type: "post",
	        data : {cmd : "selectDept"}, 
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
	        success: (data) => {
	        	let rows = data;
				$("tr[name='deptList']").empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='deptList'>";
					appendText += "<td class='text-center'><a onclick='addDept("+row.deptNo+", \""+row.deptName+"\")'>"+row.deptNo+"</td>'";
					appendText += "<td class='text-center'><a onclick='addDept("+row.deptNo+", \""+row.deptName+"\")'>"+row.deptName+"</td>'";
					appendText +="</tr>";
					
					$("#selectDept").append(appendText);
				});
	        },
	        error:function(request, err) {
	        	console.log("error");
	        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        },
	        complete: function () {
	        }
	    });
	}
	
	function loadPos(){
		$.ajax({
	        url:"adminasync", 
	        type: "post",
	        data : {cmd : "selectPos"}, 
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
	        success: (data) => {
	        	let rows = data;
				$("tr[name='posList']").empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='posList'>";
					appendText += "<td class='text-center'><a onclick='addPos("+row.posNo+", \""+row.posName+"\")'>"+row.posNo+"</td>'";
					appendText += "<td class='text-center'><a onclick='addPos("+row.posNo+", \""+row.posName+"\")'>"+row.posName+"</td>'";
					appendText +="</tr>";
					
					$("#selectPos").append(appendText);
				});
	        },
	        error:function(request, err) {
	        	console.log("error");
	        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        },
	        complete: function () {
	        }
	    });
	}
	
	// 여기서 모달값 선택시 모달 닫기 + value 넣어주기
	function addDept(no, name) {
		$("input[name='deptNo']").val(no);
		$("input[name='deptName']").val(name);
		$("#deptModal").modal('hide')
	}
	
	function addPos(no, name) {
		$("input[name='posNo']").val(no);
		$("input[name='posName']").val(name);
		$("#posModal").modal('hide')
	}
	
	// 사진 업로드
	function loadFile(input) {
	    file = input.files[0];	//선택된 파일 가져오기
		console.log("file = ", file);
		console.log("file = ", URL.createObjectURL(file));
		
	  	//새로운 이미지 추가
	    let empImage = $("#empImage");
	    empImage.attr("src", URL.createObjectURL(file));
	};

	function removeImage() {
		console.log("removeImage");
		let empImage = $("#empImage");
		empImage.attr('src', '/');
	}
	
	// write2.jsp / ajax04.jsp 참고하기
	function modifyEmpOk() {
		let empName = ($("input[name='empName']").val()).trim();
		let deptNo = ($("input[name='deptNo']").val()).trim();
		let posNo = ($("input[name='posNo']").val()).trim();
		let hiredate = ($("input[name='hiredate']").val()).trim();
		
		let imgFile = $("#changeImg");
		let empImage = $("#empImage").attr("src");
		
		if(empName.length == 0 ) {
			Swal.fire({
			  title: "미입력",
			  text: "사원명을 입력해 주세요.",
			  icon: "warning", // "success",
			  button: "확인",
			});
			return;
		}
		if(deptNo.length == 0 ) {
			Swal.fire({
			  title: "미입력",
			  text: "부서를 선택해 주세요.",
			  icon: "warning", 
			  button: "확인",
			});
			return;
		}
		if(posNo.length == 0 ) {
			Swal.fire({
			  title: "미입력",
			  text: "직급을 선택해 주세요.",
			  icon: "warning", 
			  button: "확인",
			});
			return;
		}
		console.log("empModifyForm file = ", file);
        let form = document.empModifyForm; // document.querySelector("form");
		return;
        form.appendChild( formObj('file', file) );
        form.appendChild( formObj('fileName', imgFile[0].files[0].name) );
        form.appendChild( formObj('imgFile', imgFile.val()) );
        form.appendChild( formObj('empImage', empImage) );
        form.action="admin?cmd=modifyEmpOk";
	 	form.method ="post";
		form.submit();
	}
	
	function formObj(name, value) {
        let obj = document.createElement('input');
	   	obj.setAttribute('type', 'hidden');
	  	obj.setAttribute('name', name);
	  	obj.setAttribute('value', value);
	  	return obj; 
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
                        <div class="product-status-wrap aura_content" style="padding-top: 4%;">
		                     
	                            <div class="col-lg-12 col-sm-12 col-xs-12">
		                            
		                            <div class="col-lg-2 col-sm-2 col-xs-2">
		                            </div>
		                            <%-- 찐찐 개별 --%>
		                            <div class="col-lg-5 col-sm-5 col-xs-5">
		                            	<form name="empModifyForm" action="admin">
										<!-- 사원 이름 , 부서, 직급, 입사일자 -->
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">사원번호</label>
				                                        </div>
				                                        <div class="col-lg-4 col-md-8 col-sm-8 col-xs-12">
				                                            <input type="text" name="empNo" value="${empVo.empNo}" class="form-control" readonly="readonly">
				                                        </div>
				                                        
				                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">생일</label>
				                                        </div>
				                                        <div class="col-lg-4 col-md-8 col-sm-8 col-xs-12">
				                                        	<input type="text" name="birthdate" value="${empVo.birthdate}" class="form-control" readonly="readonly" />
			                                        	</div>
				                                    </div>
				                                </div>
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">사원명</label>
				                                        </div>
				                                        <div class="col-lg-10 col-md-9 col-sm-8 col-xs-12">
				                                            <input type="text" name="empName" value="${empVo.empName}" maxlength="10" class="form-control">
				                                       	</div>
				                                    </div>
				                                </div>
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">부서</label>
				                                        </div>
				                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
				                                        	<input type="hidden" name="deptNo"  value="${empVo.deptNo}">
				                                            <input type="text" name="deptName" value="${empVo.deptName}" class="form-control" style="width: 75%; float: left;" readonly="readonly">
				                                            <button type="button" id="deptAdd" class="btn btn-custon-four btn-success" 
			                                            		data-toggle="modal" data-target=".search-dept-modal" 
			                                            		style="width: 23%; margin-left: 2%; line-height: 26px;">
			                                            		<i class="fa fa-check edu-checked-pro" aria-hidden="true"></i> 부서
				                                            </button>
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">직급</label>
				                                        </div>
				                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
				                                        	<input type="hidden" name="posNo" value="${empVo.posNo}">
				                                            <input type="text" name="posName" class="form-control" value="${empVo.posName}" style="width: 75%; float: left;" readonly="readonly">
				                                            
				                                            <button type="button" id="posAdd" class="btn btn-custon-four btn-success" 
			                                            		data-toggle="modal" data-target=".search-pos-modal" 
			                                            		style="width: 23%; margin-left: 2%; line-height: 26px;">
			                                            		<i class="fa fa-check edu-checked-pro" aria-hidden="true"></i> 직급
				                                            </button>
				                                        </div>
				                                        
				                                    </div>
				                                </div>
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">외부 이메일</label>
				                                        </div>
				                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
				                                        	<input type="text" name="empEmail" value="${empVo.empEmail}" class="form-control" readonly="readonly">
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">휴대폰</label>
				                                        </div>
				                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
				                                            <input type="text" name="cellphone" value="${empVo.cellphone}" class="form-control" readonly="readonly" />
				                                        </div>
				                                        
				                                    </div>
				                                </div>
				                                
				                                
				                                <div class="form-group-inner mg-bt-20">
				                                    <div class="row">
				                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">입사일자</label>
				                                        </div>
				                                        <div class="col-lg-4 col-md-4 col-sm-8 col-xs-12">
				                                        	<input type="date" name="hiredate" value="${empVo.hiredate}" class="form-control" pattern="\d{4}-\d{2}-\d{2}" />
			                                        	</div>
				                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
				                                            <label class="login2 pull-left pull-left-pro">퇴사일자</label>
				                                        </div>
				                                        <div class="col-lg-4 col-md-4 col-sm-8 col-xs-12">
				                                            <input type="date" name="quitdate" value="${empVo.quitdate}" class="form-control" pattern="\d{4}-\d{2}-\d{2}" />
			                                        	</div>
				                                    </div>
				                                </div>
				                             </form>
		                           		</div> 
		                           
		                           		<div class="col-lg-3 col-sm-3 col-xs-3" style="height: 420px;">
		                           			<form name="fileUpForm" method="post" enctype="multipart/form-data">
			                           			<div class="col-lg-12 text-center" style="line-height: 350px;">
		                                        	<!-- /aura/img/product/pro4.jpg || ${vo.empImage} -->
		                                        	<img id="empImage" name="empImage" class="mg-ht-10 viewImg" alt="사원이미지 없음" src="${vo.empImage}"></a>
		                                        </div>
			                           			<div class="col-lg-12 text-center">
		                                        	<!-- 
		                                        	<button type="button" id="changeImg" class="btn btn-success" style="line-height: 26px;">
					                                	<i class="fa fa-exchange" aria-hidden="true"></i> 사진 변경</button>
					                                 -->	
					                               
					                                <label class="btn btn-success" style="line-height: 26px;" for="changeImg">
													  <i class="fa fa-exchange" aria-hidden="true"></i> 사진 변경</button>
													</label>
													<input type="file" id="changeImg" style="display:none;" accept="image/*" onchange="loadFile(this)" />
					                                
					                                <%--
					                                <input type="file" name="filename" id="" />
					                                 --%>
					                                
		                                        	<button type="button" id="delImg" class="btn btn-danger bg-red" style="line-height: 26px;">
					                                	<i class="fa fa-times" aria-hidden="true"></i> 사진 삭제</button>
		                                        </div>
	                                        </form>
	                                        
		                           		</div>
	                       		</div>
		                   
		                   <div class="col-lg-12 col-sm-12 col-xs-12">
	                          			<div class="form-group-inner mg-tp-10">
	                          				<div class="login-btn-inner">
		                                        <div class="row">
		                                            <div class="col-lg-12 text-center">
		                                                <div class="cancel-wp pull-center form-bc-ele"> <!-- login-horizental -->
		                                                    <button type="button" id="resetPw" class="btn btn-danger bg-red" >
			                                        			<i class="fa fa-cog" aria-hidden="true"></i> 비밀번호 초기화
			                                        		</button>
		                                                    <button class="btn pd-setting" type="button" id="modifyEmpOk">정보 수정</button>
		                                                    <a href="admin?cmd=selectEmp" class="btn btn-default">목록</a>
		                                                </div>
		                                            </div>
		                                        </div>
		                                     </div>
		                                </div>
	                           		</div>
		                   
                        </div>
                    </div>
				</div>
			</div>
		</div>
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>
	
	
	<!-- 부서 모달 -->
	<div class="modal fade search-dept-modal" id="deptModal" tabindex="-1" role="dialog" aria-labelledby="deptModalLabel" > <!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="deptModalLabel">부서 조회</h4>
	    		</div>
	    		<div class="modal-body">
	    			<div class="product-status-wrap">
		    			<div class="asset-inner">
							<table id="selectDept">
								<tr>
									<th class="text-center col-sm-2">부서번호</th>
									<th class="text-center col-sm-4">부서명</th>
								</tr>
							</table>
						</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>
	<!-- 직급 모달 -->
	<div class="modal fade search-pos-modal" id="posModal" tabindex="-1" role="dialog" aria-labelledby="posModalLabel" > <!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="posModalLabel">직급 조회</h4>
	    		</div>
	    		<div class="modal-body">
	    			<div class="product-status-wrap">
		    			<div class="asset-inner">
							<table id="selectPos">
								<tr>
									<th class="text-center col-sm-2">직급번호</th>
									<th class="text-center col-sm-4">직급명</th>
								</tr>
							</table>
						</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>