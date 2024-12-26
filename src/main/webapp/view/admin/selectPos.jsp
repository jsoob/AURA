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
		<jsp:include page="/view/comm/header.jsp"></jsp:include>
		<div class="container-area mg-b-15">
			<div class="container-fluid">
				<div class="row">

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="product-status-wrap aura_content">

							<div class="text-right mg-bt-10">
								<div class="form-inline">

									<span class="pd-lt-10">
										<div class="form-group-inner">
											<div class="row">
												<div class="col-lg-4 col-md-3 col-sm-3 col-xs-12"></div>
												<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">

													<button type="button" id="posAdd" class="btn pd-setting"
														data-toggle="modal" data-target=".insert-pos-modal"
														style="width: 14.5%; margin-left: 2%; line-height: 23px;">직급등록
													</button>
												</div>
											</div>
										</div>
									</span>

									<div class="form-group">
										<label for="exampleInputName2">No</label> <input type="text"
											class="form-control mg-wd-10" id="exampleInputName2"
											placeholder="100">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail2">Position</label> <input
											type="email" class="form-control mg-wd-10"
											id="exampleInputEmail2" placeholder="사원">
									</div>

									<span class="pd-lt-10">
										<button type="button" class="btn pd-setting">직급 조회</button> <!-- btn-primary -> pd-setting -->
									</span>
								</div>

							</div>





							<div class="asset-inner">
								<table>
									<tr>
										<th class="text-center col-sm-2">직급번호</th>
										<th class="text-center col-sm-4">직급명</th>
										<th class="text-center col-sm-2">Setting</th>
									</tr>

									<c:forEach var="vo" items="${list}">
										<tr>
											<td class="text-center">${vo.posNo}</td>
											<td class="text-center">${vo.posName}</td>
											<td class="text-center"><a
												href="admin?cmd=modifyPos&posNo=${vo.posNo}">
													<button data-toggle="tooltip" title=""
														class="pd-setting-ed" data-original-title="수정">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
											</a> <a href="admin?cmd=deletePos&posNo=${vo.posNo}">
													<button data-toggle="tooltip" title=""
														class="pd-setting-ed" data-original-title="삭제">
														<i class="fa fa-trash-o" aria-hidden="true"></i>
													</button>
											</a></td>
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

	<!-- 직급 등록 모달 -->
	<div class="modal fade insert-pos-modal" id="posModal" tabindex="-1"
		role="dialog" aria-labelledby="posModalLabel">
		<!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="posModalLabel">직급 등록</h4>
				</div>
				<div class="modal-body">
					<div class="product-status-wrap">
						<div class="asset-inner">
							<form action="admin">
								<table id="insertPos">
									<tr>
										<th class="text-center col-sm-2">직급번호</th>
										<td>
										<input type="number" name="posNo" class="form-control" required />
										<input type="hidden" name="cmd" value="insertPosOk" /></td>

									</tr>
									<tr>
										<th class="text-center col-sm-4">직급명</th>
										<td><input type="text" name="posName"
											class="form-control" required /></td>
									</tr>
									<tr>

										<td colspan="2" class="text-end"><input type="submit"
											value="등록" class="btn btn-primary pd-setting" /></td>
									</tr>

								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>