<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

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
                <!-- 여기부터 개별 -->
                <div class="row">
                    
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                            
                            <%-- 검색 부분 --%>
                            <div class="text-right mg-bt-10">
                            	<div class="form-inline">
								  	<div class="form-group">
									    <label for="exampleInputName2">Name</label>
									    <input type="text" class="form-control mg-wd-10" id="exampleInputName2" placeholder="Jane Doe">
									  </div>
									  <div class="form-group">
									    <label for="exampleInputEmail2">Email</label>
									    <input type="email" class="form-control mg-wd-10" id="exampleInputEmail2" placeholder="jane.doe@example.com">
									  </div>
								  
								  	<span class="pd-lt-10">
								  		<button type="button" class="btn pd-setting">사원 조회</button><!-- btn-primary -> pd-setting -->
								  	</span>
								  </div>
								
                            </div>
                            
                            <%-- 테이블 부분 --%>
                            <div class="asset-inner">
                                <table>
                                	<%-- 테이블 컬럼 --%>
                                    <tr>
                                        <th>부서명</th>
										<th>사원번호</th>
										<th>사원명</th>
										<th>직급</th>
										<th>외부이메일</th>
										<th>입사일자</th>
                                    </tr>
                                    <!-- <td><img src="img/product/book-1.jpg" alt=""></td> -->
                                	<%-- 테이블 값 --%>
                                    <tr>
                                        <td>1</td>
                                        <td>Web Development Book</td>
                                        <td>Html, Css</td>
                                        <td>CSE</td>
                                    </tr>
                                </table>
                            </div>
                            <%-- 테이블 페이징 처리 --%>
                            <!-- 
                            <div class="custom-pagination">
								<ul class="pagination">
									<li class="page-item"><a class="page-link" href="#">Previous</a></li>
									<li class="page-item"><a class="page-link" href="#">1</a></li>
									<li class="page-item"><a class="page-link" href="#">2</a></li>
									<li class="page-item"><a class="page-link" href="#">3</a></li>
									<li class="page-item"><a class="page-link" href="#">Next</a></li>
								</ul>
                            </div>
                             -->
                        </div>
                    </div>
                    
                </div>
                
                <!-- 이전 -->
                <%-- 
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<a href="admin?cmd=insertPos">
						<input type="button" class="btn btn-outline-primary" value="직급등록" />
					</a>
					<table class="table" width="100px" height="100px">
						<tr>
							<th>부서명</th>
							<th>사원번호</th>
							<th>사원명</th>
							<th>직급</th>
							<th>외부이메일</th>
							<th>입사일자</th>
						</tr>
						<c:forEach var="vo" items="${list}">>
							<tr>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.deptName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empNo}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posName}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.empEmail}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.hiredate}</a></td>
							</tr>
						</c:forEach>
					</table>    		
                </div>
                 --%>
            </div>
        </div>
        
        <jsp:include page="/view/comm/footer.jsp"></jsp:include>
    </div>
	
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>