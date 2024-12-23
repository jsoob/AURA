<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<%-- <jsp:include page="/view/comm/headCss.jsp"></jsp:include> --%>
<%-- <jsp:include page="/view/comm/getPwCss.jsp"></jsp:include> --%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/auraCss/getPwCss.css?after">

<!-- 
<style type="text/css">
    * {
	    box-sizing: border-box;
	}
	
	body {
	    height: 100%;
	    font-size: 14px;
	    font-weight: 400;
	    background: #F6F8FA;
    }

	/* 전체 화면 */
	.error-pagewrap {
	    text-align: center;
	    height: 800px;
	}
	
	/* 화면 contents */
	.error-page-int {
	    max-width: 550px;
	    padding: 20px 0;
	    width: 80%;
	    position: relative;
	    margin: 0 auto;
	}
	.error-page-int, .error-pagewrap:before {
	    top: 50%;
    	transform: translateY(-50%);
	    /* 
	    vertical-align: middle;
	    display: inline-block;
	     */
	}

	h3 {
	    margin: 0 0 10px;
	    font-weight: 700;
	}
	
	/* PASSWORD RECOVER */
	.custom-login h3, .ps-recovered h3 {
	    font-size: 20px;
	    color: #303030;
	}
	/* Please fill the form to recover your password */
	.custom-login p, .ps-recovered p {
	    font-size: 14px;
	    color: #303030;
	}
	
	
	/* content-error */
	.content-error {
		text-align: center;
	    margin: 0px 20px;
	}
	
	.content-error .hpanel {
	    text-align: left;
	}
	
	.hpanel .panel-body {
	    background: #fff;
	    border-radius: 2px;
	    padding: 20px;
	    position: relative;
	}
	
	.content-error p {
	    font-size: 14px;
	    color: #444;
	    line-height: 24px;
	}
	
	.help-block {
	    display: block;
	    margin-top: 5px;
	    margin-bottom: 10px;
	    color: #737373;
	}
	
	.small, small {
	    font-size: 85%;
	}
	
	.poss-recover .btn-success {
	    color: #fff;
	    background-color: #006DF0;
	    border-color: #006DF0;
	}
	
	.btn-block {
	    display: block;
	    width: 100%;
	}
	
	/* copylight */
	.login-footer {
	    margin-top: 15px;
	}
	
	
	.text-center {
	    text-align: center;
	}
	.form-group {
	    margin-bottom: 15px;
	}
	
	.btn {
	    margin-bottom: 0;
	    font-weight: 400;
	    text-align: center;
	    white-space: nowrap;
	    vertical-align: middle;
	    touch-action: manipulation;
	    cursor: pointer;
	    background-image: none;
	    border: 1px solid transparent;
	    padding: 6px 12px;
	    font-size: 14px;
	    line-height: 1.42857143;
	    border-radius: 4px;
	    user-select: none;
	}
	
	label {
	    display: inline-block;
	    max-width: 100%;
	    margin-bottom: 5px;
	    font-weight: 700;
	}
	
	.form-control {
	    background-color: #FFFFFF;
	    background-image: none;
	    border: 1px solid #e5e6e7;
        font-size: 14px;
	    border-radius: 1px;
	    line-height: 25px;
	    color: inherit;
	    display: block;
	    padding: 6px 12px;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    width: 100%;
	    box-shadow: none;
	}
	
	.wd-50 {
		width: 50%;
		float: left;
		padding: 0px 5px;
	}
</style>
 -->
<!-- error msg -->
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>
	<div class="error-pagewrap">
		<div class="error-page-int">
			<div class="text-center ps-recovered">
				<h3>비밀번호 찾기</h3>
				<p>비밀번호를 복구하려면 양식을 작성해 주세요.</p>
			</div>
			<div class="content-error">
				<div class="hpanel">
                    <div class="panel-body poss-recover">
                        <p>
                            사원번호와 이메일 주소를 입력하시면 해당 이메일로 인증번호가 전송됩니다.
                        </p>
                        <form action="#" id="loginForm">
                            <div class="wd-50 form-group">
                                <label class="control-label" for="empNo">사원번호</label>
                                <input type="text" title="Please enter you email adress" 
                                	required name="empNo" id="empNo" class="form-control">
                                <span class="help-block small"> 귀하의 사원번호</span>
                            </div>
                            <div class="wd-50 form-group">
                                <label class="control-label" for="empEmail">Email</label>
                                <input type="text" placeholder="example@gmail.com" title="Please enter you email adress" 
                                	required name="empEmail" id="empEmail" class="form-control">
                                <span class="help-block small"> 귀하의 등록된 외부 이메일 주소<br>(google, naver)</span>
                            </div>
                            <button class="btn btn-success btn-block">인증메일 발송</button>
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