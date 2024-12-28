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
                
                <%-- 사원 정보 + 근태 + 부서게시판 --%>
                <div class="row mg-bt-10">
                    <div class="col-lg-3 wdps-20">
                        <div class="product-status-wrap" style="height: 320px;">
                           <div class="row mg-bt-10">
	                          	<div class="col-lg-12 col-sm-12 col-xs-12 myImageDiv text-center">
		                       		<div class="col-lg-12 mg-bt-10" style="height: 200px;">
		                       			<img class="myViewImg" alt="사원이미지 없음" src="${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}">
		                       		</div>
		                       		<div class="col-lg-12">
		                       			<label>${loginEmp.empName} ${loginEmp.posName}
		                       				<c:if test="${loginEmp.deptName} != '' ">
		                       					( ${loginEmp.deptName} )
		                       				</c:if>
		                       			</label>
	                              	</div>
		                       	</div>
                           </div>
                           
                           <c:if test="${loginEmp.empNo != 2024000 }">
		                        <div class="row">
		                          <div class="col-lg-12 text-center">
										<button type="button" id="mainStart" class="btn pd-setting">출근</button>
										<button type="button" id="mainStart" class="btn ds-setting">퇴근</button>
		                          </div>
	                           </div>
                           </c:if>
                        </div>
                    </div>
                    
                    <div class="col-lg-9 wdps-80 pd-lt-nn">
                        <div class="product-status-wrap">
                           <div class="row" style="height: 280px;">
	                          	<div class="col-lg-12 col-sm-12 col-xs-12">
                           			<h4>부서게시판</h4>
                           		</div>
                           		
	                          	<div class="col-lg-6 col-sm-6 col-xs-6">
                           			<label>전체글(${deptBTotalCount })</label>
                           		</div>
	                          	<div class="col-lg-6 col-sm-6 col-xs-6 text-right">
                           			<a href="${pageContext.request.contextPath}/deptboard">더보기 <i class="fa fa-chevron-right" aria-hidden="true"></i></a>
                           		</div>
                           		
	                          	<div class="col-lg-12 col-sm-12 col-xs-12 text-center">
		                       		<table class="table table-striped">
										<tr>
											<th>게시판번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>등록일자</th>
											<th>조회수</th>
										</tr>
										<tbody class="deptBList">
											<c:forEach var="deptBvo" items="${deptBList}">
												<tr>
													<td><a href="deptboard?cmd=detailDeptB&deptBNo=${deptBvo.deptBNo}">${deptBvo.deptBNo}</a></td>
													<td><a href="deptboard?cmd=detailDeptB&deptBNo=${deptBvo.deptBNo}">${deptBvo.deptBTitle}</a></td>
													<td><a href="deptboard?cmd=detailDeptB&deptBNo=${deptBvo.deptBNo}">${deptBvo.deptBCrtr}</a></td>
													<td><a href="deptboard?cmd=detailDeptB&deptBNo=${deptBvo.deptBNo}">${deptBvo.createDate}</a></td>
													<td><a href="deptboard?cmd=detailDeptB&deptBNo=${deptBvo.deptBNo}">${deptBvo.freeBView}</a></td>
												</tr>
											</c:forEach>
										</tbody>							
									</table>
		                       	</div>
                           </div>
                        </div>
                    </div>
                </div>
                
                <%-- 달력 + 자유게시판 --%>
                <div class="row">
                    <div class="col-lg-3 wdps-20">
                        <div class="product-status-wrap" style="height: 320px;">
                           	<%-- <jsp:include page="/view/comm/calendar.jsp"></jsp:include> --%>
                        </div>
                    </div>
                    
                    <div class="col-lg-9 wdps-80 pd-lt-nn">
                        <div class="product-status-wrap">
                           <div class="row" style="height: 280px;">
	                          	<div class="col-lg-12 col-sm-12 col-xs-12">
                           			<h4>자유게시판</h4>
                           		</div>
                           		
	                          	<div class="col-lg-6 col-sm-6 col-xs-6">
                           			<label>전체글(${freeBTotalCount })</label>
                           		</div>
	                          	<div class="col-lg-6 col-sm-6 col-xs-6 text-right">
                           			<a href="${pageContext.request.contextPath}/freeboard">더보기 <i class="fa fa-chevron-right" aria-hidden="true"></i></a>
                           		</div>
                           		
	                          	<div class="col-lg-12 col-sm-12 col-xs-12 text-center">
		                       		<table class="table table-striped">
										<tr>
											<th>게시판번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>등록일자</th>
											<th>조회수</th>
										</tr>
										<tbody class="freeBList">
											<c:forEach var="freeBvo" items="${freeBList}">
												<tr>
													<td><a href="freeboard?cmd=detailFreeB&freeBNo=${freeBvo.freeBNo}">${freeBvo.freeBNo}</a></td>
													<td><a href="freeboard?cmd=detailFreeB&freeBNo=${freeBvo.freeBNo}">${freeBvo.freeBTitle}</a></td>
													<td><a href="freeboard?cmd=detailFreeB&freeBNo=${freeBvo.freeBNo}">${freeBvo.freeBCrtr}</a></td>
													<td><a href="freeboard?cmd=detailFreeB&freeBNo=${freeBvo.freeBNo}">${freeBvo.createDate}</a></td>
													<td><a href="freeboard?cmd=detailFreeB&freeBNo=${freeBvo.freeBNo}">${freeBvo.freeBView}</a></td>
												</tr>
											</c:forEach>
										</tbody>							
									</table>
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
