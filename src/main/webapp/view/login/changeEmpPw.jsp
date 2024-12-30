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
			let code1 = ($("#code1").val()).trim();
			let code2 = ($("#code2").val()).trim();
			if(code1.length > 0 ) {
				let passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@~#$%^&*?_]).{8,16}$/;
				
				if (passwordRegex.test(code1)) {
					if(code2.length == 0) {
						Swal.fire({
							title : 'Error', 
							text : '비밀번호 재확인을 입력해 주세요.', 
							icon : 'error', 
						});
					} else if(code1==code2) {
						let form = document.createElement('form');
					  	
		        		let obj = document.createElement('input');
					   	obj.setAttribute('type', 'hidden');
					  	obj.setAttribute('name', 'empNo');
					  	obj.setAttribute('value', ${empNo});
					  	form.appendChild(obj);
					  	
					    let obj2 = document.createElement('input');
					   	obj2.setAttribute('type', 'hidden');
					  	obj2.setAttribute('name', 'code1');
					  	obj2.setAttribute('value', code1);
					  	form.appendChild(obj2);
		        		
					  	form.setAttribute('method', 'post');
					  	form.setAttribute('action', 'login?cmd=changeEmpPwOk');
					    document.body.appendChild(form);
					    form.submit();
					} else {
						Swal.fire({
							title : 'Error', 
							text : '비밀번호가 일치하지 않습니다.다시 입력해 주세요.', 
							icon : 'error', 
						});
					}
				} else {
					Swal.fire({
						title : 'Error', 
						text : '8~16자의 영문 대/소문자, 숫자, 특수기호를 조합하여 비밀번호를 다시 입력해주세요.', 
						icon : 'error', 
					});
				}
			} else {
				Swal.fire({
					title : 'Error', 
					text : '비밀번호를 입력해 주세요.', 
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
                                <label class="control-label" for="code1">비밀번호 변경</label>
                                <input type="text" id="code1" name="code1" class="form-control">
                                <span class="help-block small">8~16자의 영문 대/소문자, 숫자, 특수기호를 조합하여 입력해주세요.(!,@,~만 허용)</span>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="code2">비밀번호 재확인</label>
                                <input type="text" id="code2" name="code2" class="form-control">
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