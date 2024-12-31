<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="jakarta.tags.core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animated Form Login</title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginform/login.css">
<%--
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<script type="text/javascript">
	$( ()=> {
		$("#login").on("click", ()=> {
			login();	
		});
	});
	
	function enterkey() {
	  if (window.event.keyCode == 13) {
	       // 엔터키가 눌렸을 때 실행할 내용
	       login();
	  }
	}
	
	function login() {
		let empNo = ($("#empNo").val()).trim();
		let psswd = ($("#psswd").val()).trim();
		
		if(empNo.length > 0 && psswd.length > 0 ) {
			// 이렇게 보내고 있어요 성공하면 main 화면 / 실패시 다시 login 화면 이동
	        $.ajax({
	        	url:"loginasync",
	            type: "post",
				data: { 
					"cmd" : "loginOk", 
					"empNo" : empNo, 
					"psswd" : psswd
				}, 
				dataType: 'json', 
	            success: (data) => {
	            	let status = data.status;
	            	if(status == 0) { 
	            		Swal.fire({
	          			  title: "Error",
	          			  text: "사원번호 또는 비밀번호가 틀렸습니다. 다시 입력해 주세요.",
	          			  icon: "warning", // "success",
	          			  button: "확인",
	          			});
		            } if(status == 1) {
						let form = document.loginForm;
					 	form.action="main";
					 	form.method ="post";
						form.submit();
		            }
            	}
	        });
		} else {
			Swal.fire({
				title : 'Error', 
				text : '사원번호 또는 비밀번호를 입력해 주세요.', 
				icon : 'error', 
			});
			
			/* 
			swal({
			  title: "미입력",
			  text: "사원번호 또는 비밀번호를 입력해 주세요.",
			  icon: "warning", // "success",
			  button: "확인",
			});
			 */
		}
	}
</script>

</head>
<body>
	<%-- <c:if test="${loginEmp.emp_no == null or loginEmp.emp_pw == ''}">  --%>
	<div class="lContainer">
    	<div class="lContents">
	        <i></i>
	        <i></i>
	        <i></i>
	        <div class="login">
	            <div class="logo"><img src="/aura/img/logo/AURAlogo.png" /></div>
	            <form name="loginForm">
	            	<div class="input-box">
	            		<%-- <input type="hidden" name="cmd" value="loginOk" /> --%>
		            	<input type="text" id="empNo" name="empNo" class="form-input" value="2024000" placeholder="사원번호" maxlength="10" onkeyup="enterkey()" > <!--  placeholder="사원번호" -->
		            </div>
		            <div class="input-box">
		                <input type="password" id="psswd" name="psswd" class="form-input" value="2024000" placeholder="비밀번호" maxlength="15" onkeyup="enterkey()" > <!-- placeholder="비밀번호" -->
		            </div>
		            
		            <div class="input-box">
		                <input type="button" id="login" value="Sign In">
		            </div>
	            </form>
	            
	            <div class="links">
	                <a href="login?cmd=getPass">Forgot Password?</a>
	            </div>
	        </div>
	    </div>
    </div>
	
</body>
</html>