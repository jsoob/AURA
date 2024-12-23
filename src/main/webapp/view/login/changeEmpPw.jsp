<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증번호 발송</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

<script type="text/javascript">
	$( ()=> {
		let cnt = 0;
		
		$("#btnSend").on("click", ()=> {
			let code = ($("#code").val()).trim();
			cnt++;
			if(code.length > 0 ) {
				/* 
				let  num_check=/^[0-9]*$/;
				
				if(num_check.test(code)) {
				 */
					$.ajax({
			        	url:"loginasync", 
			            type: "post",
						data: { 
							"cmd" : "findEmailEmpOk", 
							"code" : code, 
							"authCode" : $("#btnSend").data("authCode")
						}, 
						dataType: 'json', 
			            success: (data) => {
			            	let status = data.status;
			            	console.log("status = ", status);
			            	console.log("cnt = ", cnt);
			            	if(status == 0) {
				            	console.log("status == 0");
			            		if(cnt== 5) {
					            	console.log("cnt == 5");
			            			location.reload(true); // 5회 이상 틀릴시 새로고침
			            		}
			            		$("#msg").text("실패 "+cnt+"회(5회시 인증번호 재발송)");
				            } else if(status == 1) { // 1이면 조회
				            	console.log("status == 1");
				            	
				            	let form = document.createElement('form');
			    			  	
			            		let obj = document.createElement('input');
			    			   	obj.setAttribute('type', 'hidden');
			    			  	obj.setAttribute('name', 'empNo');
			    			  	obj.setAttribute('value', ${empNo});
			    			  	form.appendChild(obj);
			    			  	
			    			    let obj2 = document.createElement('input');
			    			   	obj2.setAttribute('type', 'hidden');
			    			  	obj2.setAttribute('name', 'empName');
			    			  	obj2.setAttribute('value', "${empName}");
			    			  	form.appendChild(obj2);
			            		
			    			  	form.setAttribute('method', 'post');
			    			  	form.setAttribute('action', 'login?cmd=changeEmpPw');
			    			    document.body.appendChild(form);
			    			    form.submit();
			            	}
		            	}
			        });
			} else {
				Swal.fire({
					title : 'Error', 
					text : '인증번호를 입력해 주세요.', 
					icon : 'error', 
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
				<h3>사원 비밀번호 변경</h3>
				<p>비밀번호를 변경하려면 양식을 작성해 주세요.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
                    <div class="panel-body poss-recover">
                        <p>
                            변경할 비밀번호를 입력하세요.
                        </p>
                        <div>
                            <div class="form-group">
                                <label class="control-label" for="code">비밀번호 변경</label>
                                <input type="text" id="code" name="code" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="code">비밀번호 재확인</label>
                                <input type="text" id="code" name="code" class="form-control">
                            </div>
                            <button id="btnSend" class="btn btn-success btn-block">확인</button>
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