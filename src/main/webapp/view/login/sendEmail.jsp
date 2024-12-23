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
		$("#btnSend").on("click", ()=> {
			let empNo = ($("#empNo").val()).trim();
			let empEmail = ($("#empEmail").val()).trim();
			
			if(empno.length > 0 && empEmail.length > 0 ) {
	            let form = document.querySelector("form");
			 	form.action="login";
			 	form.method ="post";
				form.submit();
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
				<h3>인증번호 입력</h3>
				<p>비밀번호를 복구하려면 양식을 작성해 주세요.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
                    <div class="panel-body poss-recover">
                        <p>
                            해당 이메일로 인증번호가 발송됐습니다. 이메일 인증번호를 입력하세요.
                        </p>
                        <form action="#" id="loginForm">
                            <div class="form-group">
                                <label class="control-label" for="emailNo">인증번호</label>
	            				<input type="hidden" name="cmd" value="sendEmailOk" />
                                <input type="text" title="Please enter you email adress" 
                                	required id="empNo" name="empNo" class="form-control">
                                <span class="help-block small"> 귀하의 사원번호</span>
                            </div>
                            <button id="btnSend" class="btn btn-success btn-block">인증메일 발송</button>
                        </form>
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