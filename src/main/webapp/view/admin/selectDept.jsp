<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
</head>
<body>

	<!-- Start Left menu area -->
	<jsp:include page="/view/comm/sidebar.jsp"></jsp:include>

	<!-- End Left menu area -->
	<!-- Start Welcome area -->
	<div class="all-content-wrapper">
		 <!--  header 가서 부서관리 부분 하이퍼링크 연결  -->
		<jsp:include page="/view/comm/header.jsp"></jsp:include>
		
		<div class="container-area mg-b-15">
			<div class="container-fluid">
				<div class="row">

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="product-status-wrap">

							<div class="text-right mg-bt-10">
								<a href="admin?cmd=insertDept"> <input type="button"
									class="btn pd-setting" value="부서등록 " />
								</a>
							</div>

							<div class="asset-inner">
								<table>
									<tr>
										<th class="text-center col-sm-2">부서번호 </th>
										<th class="text-center col-sm-4">부서명 </th>
										<th class="text-center col-sm-2">Setting</th>
									</tr>

									<c:forEach var="vo" items="${list}">
										<tr>
											<td class="text-center"><a
												href="admin?cmd=modifyDept&deptNo=${vo.deptNo}">${vo.deptNo}</a></td>
											<td class="text-center">${vo.deptName}</a></td>
											<td class="text-center">
												<a href="admin?cmd=modifyDept&deptNo=${vo.deptNo}">
													<button data-toggle="tooltip" title="" class="pd-setting-ed"
														data-original-title="Edit">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button></a>
												<a href="admin?cmd=deleteDept&deptNo=${vo.deptNo}">	
												<button data-toggle="tooltip" title="" class="pd-setting-ed"
													data-original-title="Trash">
													<i class="fa fa-trash-o" aria-hidden="true"></i>
												</button></a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<%-- 템플릿 페이징 일단 주석 --%>
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

				<%-- 이전 코드 --%>
				<%--
				<div class="row">
					<a href="admin?cmd=insertPos"><input type="button"
						class="btn btn-outline-primary" value="직급등록" /></a>
					<table class="table" width="100px" height="100px">
						<tr>
							<th>직급번호</th>
							<th>직급명</th>
						</tr>
						<c:forEach var="vo" items="${list}">
							<tr>

								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posNo}</a></td>
								<td><a href="admin?cmd=modifyPos&posNo=${vo.posNo}">${vo.posName}</a></td>
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