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
		
		$("#btnSend").data("authCode", ${authCode});
		
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
			            	
			            	if(status == 0) {
				            	if(cnt== 5) {
					            	location.reload(true); // 5회 이상 틀릴시 새로고침
			            		}
			            		$("#msg").text("실패 "+cnt+"회(5회시 인증번호 재발송)");
				            } else if(status == 1) { // 1이면 조회
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
				/* 
				} else {
					Swal.fire({
          			  title: "Error",
          			  text: "인증번호 양식이 맞지 않습니다. 메일을 다시 확인해 주세요.",
          			  icon: "warning", 
          			  button: "확인",
          			});
				}
				 */
			} else {
				Swal.fire({
					title : 'Error',         // Alert 제목
					text : '인증번호를 입력해 주세요.',  // Alert 내용
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
				<h3>사원 비밀번호 복구 인증번호 입력</h3>
				<p>비밀번호를 복구하려면 양식을 작성해 주세요.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
                    <div class="panel-body poss-recover">
                        <p>
                            해당 이메일로 인증번호가 발송됐습니다. 이메일 인증번호를 입력하세요.
                            <span id="msg" style="color: red;"></span>
                        </p>
                        <div>
                            <div class="form-group">
                                <label class="control-label" for="code">인증번호</label>
                                <input type="text" id="code" name="code" class="form-control">
                                <span class="help-block small"> 메일 인증번호</span>
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