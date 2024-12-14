<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animated Form Login</title>

<link rel="stylesheet" href="../../css/loginform/login.css">    
    
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> 
	
<script type="text/javascript">
	$( ()=> {
		
		$("#login").on("click", ()=> {
			let arm_empno = ($("#arm_empno").val()).trim();
			let arm_password = ($("#arm_password").val()).trim();
			
			if(arm_empno.length > 0 && arm_password.length > 0 ) {
				let form = document.querySelector("form");
	            // form.action = "loginOk.jsp"; 
	            form.method ="post";
	            form.submit();
			} else {
				swal({
				  title: "미입력",
				  text: "사원번호 또는 비밀번호를 입력해 주세요.",
				  icon: "warning", // "success",
				  button: "확인",
				});
			}
		});
	});
</script>

</head>
<body>
	<div class="lContainer">
    	<div class="lContents">
	        <i></i>
	        <i></i>
	        <i></i>
	        <div class="login">
	            <div class="logo"><img src="https://logowik.com/content/uploads/images/aura-digital-safety4284.logowik.com.webp" alt="" /></div>
	            
	            <form action="login?cmd=loinOk" >
	            	<div class="input-box">
		            	<input type="hidden" id="arm_no" name="arm_no" />
		                <input type="text" id="arm_empno" name="arm_empno" class="form-input" placeholder="사원번호" maxlength="10" > <!--  placeholder="사원번호" -->
		            </div>
		            <div class="input-box">
		                <input type="password" id="arm_password" name="arm_password" class="form-input" placeholder="비밀번호" maxlength="15" > <!-- placeholder="비밀번호" -->
		            </div>
		            
		            <div class="input-box">
		                <input type="button" id="login" value="Sign In">
		            </div>
	            </form>
	            
	            <div class="links">
	                <a href="getPass.jsp">Forgot Password?</a>
	            </div>
	        </div>
	    </div>
    </div>
</body>
</html>