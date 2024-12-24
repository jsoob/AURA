<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

<script type="text/javascript">
	$( ()=> {
		$("#btnSend").on("click", ()=> {
			let empNo = ($("#empNo").val()).trim();
			
			if(empNo.length > 0 ) {
			    
			  	$.ajax({
		        	url:"loginasync", 
		            type: "post",
					data: { 
						"cmd" : "findEmailEmp", 
						"empNo" : empNo
					}, 
					dataType: 'json', 
		            success: (data) => {
		            	let getEmailEmp = data.getEmailEmp;
		            	let status = data.status;
		            	console.log("status = ", status);
		            	console.log("getEmailEmp = ", getEmailEmp);
		            	if(status == 0) { 
		            		Swal.fire({
		          			  title: "Error",
		          			  text: "사원번호를 찾을 수 없습니다. 다시 입력해 주세요.",
		          			  icon: "warning", // "success",
		          			  button: "확인",
		          			});
			            } else if(status == 2) {
		            		Swal.fire({
			          			  title: "이메일 조회 불가",
			          			  text: "이메일 정보가 없습니다. 관리자에게 문의하세요.",
			          			  icon: "warning", // "success",
			          			  button: "확인",
			          			});
			            } else if(status == 3) {
		            		Swal.fire({
		          			  title: "Error",
		          			  text: "잘못 입력됐습니다. 다시 입력해 주세요. (숫자형식)",
		          			  icon: "warning", // "success",
		          			  button: "확인",
		          			});
			            } else if(status == 1) { // 1이면 조회
			            	let form = document.createElement('form');
		    			  	
		            		let obj = document.createElement('input');
		    			   	obj.setAttribute('type', 'hidden');
		    			  	obj.setAttribute('name', 'empNo');
		    			  	obj.setAttribute('value', empNo);
		    			  	form.appendChild(obj);
		    			  	
		    			    let obj2 = document.createElement('input');
		    			   	obj2.setAttribute('type', 'hidden');
		    			  	obj2.setAttribute('name', 'empName');
		    			  	obj2.setAttribute('value', getEmailEmp.empName);
		    			  	form.appendChild(obj2);
		    			  	
		    			    let obj3 = document.createElement('input');
		    			   	obj3.setAttribute('type', 'hidden');
		    			  	obj3.setAttribute('name', 'empEmail');
		    			  	obj3.setAttribute('value', getEmailEmp.empEmail);
		    			  	form.appendChild(obj3);
		            		
		    			  	form.setAttribute('method', 'post');
		    			  	form.setAttribute('action', 'login?cmd=sendEmail');
		    			    document.body.appendChild(form);
		    			    form.submit();
		            	}
	            	}
		        });
			} else {
				Swal.fire({
					title : 'Error',         // Alert 제목
					text : '사원번호 또는 이메일을 입력해 주세요.',  // Alert 내용
					icon : 'error',                         // Alert 타입
				});
			}
		});
	});
</script>

</head>
<body>
	<div class="error-pagewrap">
		<div class="error-page-int">
			<div class="text-center ps-recovered">
				<h2><i class="fa fa-lock fa-pwLock" aria-hidden="true"></i></h2>
				<h3>비밀번호 찾기</h3>
				<p>비밀번호를 복구하려면 양식을 작성해 주세요.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
                    <div class="panel-body poss-recover">
                        <p>
                            사원번호와 이메일 주소를 입력하시면 해당 이메일로 인증번호가 전송됩니다.
                        </p>
                           <div id="sendForm" class="row">
                           	<div class="col-sm-12 form-group"> <!-- wd-50 -->
                                <label class="control-label" for="empNo">사원번호</label>
                                <input type="text" title="Please enter you email adress" 
                                	id="empNo" name="empNo" class="form-control">
	            				<%--
	            					<input type="hidden" name="cmd" value="sendEmail" />
	            				 --%>
                                <span class="help-block small"> 귀하의 사원번호</span>
                            </div>
                            <!-- 
                            <div class="col-sm-6 form-group">
                                <label class="control-label" for="empEmail">Email</label>
                                <input type="text" placeholder="example@gmail.com" title="Please enter you email adress" 
                                	id="empEmail" name="empEmail" class="form-control">
                                <span class="help-block small"> 귀하의 등록된 외부 이메일 주소<br>(google, naver)</span>
                            </div>
                             -->
                           	<button id="btnSend" class="btn btn-success btn-block">인증메일 발송</button>
                           </div>
                    </div>
                </div>
			</div>
			<div class="text-center login-footer">
				<p>Copyright © 2024. All rights reserved. Template by AURA</p>
			</div>
		</div>   
    </div>
</body>
</html>