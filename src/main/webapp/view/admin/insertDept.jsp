<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							<div class="col-lg-2 col-lg-2 col-sm-2 col-xs-2"></div>
							<%-- 찐찐 개별 --%>
							<div class="col-lg-8 col-lg-8 col-sm-12 col-xs-12">
								<form action="admin">
									<table class="table">

										<tr>
											<th>부서명</th>
											<td><input type="text" name="deptName"
												class="form-control" placeholder="부서명 입력" /></td>
										</tr>

										<tr>
											<td colspan="2"><a href="admin?cmd=selectDept"
												class="btn btn-outline-primary"> 취소 </a> <input
												type="submit" class="btn pd-setting" value="등록" /></td>
										</tr>

									</table>

								</form>
							</div>
						</div>
					</div>
				</div>
				<jsp:include page="/view/comm/footer.jsp"></jsp:include>
			</div>

			<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>