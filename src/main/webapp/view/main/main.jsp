<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/auraCss/main.css">

</head>
<body>
	<%-- Start Left menu area --%>
    <jsp:include page="/view/comm/sidebar.jsp"></jsp:include>
    
    <%-- End Left menu area --%>
    <%-- Start Welcome area --%>
	<div class="all-content-wrapper">
        <jsp:include page="/view/comm/header.jsp"></jsp:include>
        
        <div class="container-area mg-b-15">
            <div class="container-fluid">
                
                <div class="row">
                
                    <div class="col-lg-3 wd-20">
                        <div class="product-status-wrap">
                           <div class="row">
	                          	<div class="col-lg-12 col-sm-12 col-xs-12 myImageDiv text-center">
		                       		<div class="col-lg-12 mg-bt-10" style="height: 200px;">
		                       			<img class="myViewImg" alt="사원이미지 없음" src="${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}">
		                       		</div>
		                       		<div class="col-lg-12">
		                       			<span>${loginEmp.empName}${loginEmp.posName}</span>
		                       			<br>
		                           		<span>${loginEmp.deptName}</span>
	                              	</div>
		                       	</div>
                           </div>
                        </div>
                    </div>
                    
                    <div class="col-lg-3 wd-80">
                        <div class="product-status-wrap">
                           <div class="row">
	                          	<div class="col-lg-12 col-sm-12 col-xs-12 text-center">
		                       		
		                       	</div>
                           </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="/view/comm/footer.jsp"></jsp:include>
    </div>
	
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>
