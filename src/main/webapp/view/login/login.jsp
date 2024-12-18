<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="jakarta.tags.core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Animated Form Login</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginform/login.css">    
    
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> 
	
<script type="text/javascript">
	$( ()=> {
		$("#login").on("click", ()=> {
			let empno = ($("#empno").val()).trim();
			let psswd = ($("#psswd").val()).trim();
			
			if(empno.length > 0 && psswd.length > 0 ) {
				// location.href = "login?cmd=loginOk"; 
	            let form = document.querySelector("form");
			 	form.action="login";
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
	<%-- <c:if test="${loginEmp.emp_no == null or loginEmp.emp_pw == ''}">  --%>
	<div class="lContainer">
    	<div class="lContents">
	        <i></i>
	        <i></i>
	        <i></i>
	        <div class="login">
	            <div class="logo"><img src="https://logowik.com/content/uploads/images/aura-digital-safety4284.logowik.com.webp" alt="" /></div>
	            <form name="loginForm">
	            	<div class="input-box">
	            		<input type="hidden" name="cmd" value="loginOk" />
		            	<input type="text" id="empno" name="empno" class="form-input" value="2024000" placeholder="사원번호" maxlength="10" > <!--  placeholder="사원번호" -->
		            </div>
		            <div class="input-box">
		                <input type="password" id="psswd" name="psswd" class="form-input" value="2024000" placeholder="비밀번호" maxlength="15" > <!-- placeholder="비밀번호" -->
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