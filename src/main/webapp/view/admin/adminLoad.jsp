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
                            
                            <div class="text-right mg-bt-10">
                            	<a href="admin?cmd=insertPos">
									<input type="button" class="btn btn-primary" value="직급등록" />
								</a>
                            </div>
                            
                            <div class="asset-inner">
                                <table>
                                    <tr>
                                        <th>부서명</th>
										<th>사원번호</th>
										<th>사원명</th>
										<th>직급</th>
										<th>외부이메일</th>
										<th>입사일자</th>
										<th>Setting</th>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td><img src="img/product/book-1.jpg" alt=""></td>
                                        <td>Web Development Book</td>
                                        <td>
                                            <button class="pd-setting">Active</button>
                                        </td>
                                        <td>Html, Css</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td><img src="img/product/book-2.jpg" alt=""></td>
                                        <td>Quality Bol pen</td>
                                        <td>
                                            <button class="ps-setting">Paused</button>
                                        </td>
                                        <td>PHP</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td><img src="img/product/book-3.jpg" alt=""></td>
                                        <td>Box of pendrive</td>
                                        <td>
                                            <button class="ds-setting">Disabled</button>
                                        </td>
                                        <td>Java</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td><img src="img/product/book-4.jpg" alt=""></td>
                                        <td>Quality Bol pen</td>
                                        <td>
                                            <button class="pd-setting">Active</button>
                                        </td>
                                        <td>PHP</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td><img src="img/product/book-1.jpg" alt=""></td>
                                        <td>Web Development Book</td>
                                        <td>
                                            <button class="pd-setting">Active</button>
                                        </td>
                                        <td>Wordpress</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td><img src="img/product/book-2.jpg" alt=""></td>
                                        <td>Quality Bol pen</td>
                                        <td>
                                            <button class="ps-setting">Paused</button>
                                        </td>
                                        <td>Java</td>
                                        <td>CSE</td>
                                        <td>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Edit"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            <button data-toggle="tooltip" title="" class="pd-setting-ed" data-original-title="Trash"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
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