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

					<form action="admin">
						<table class="table">
							<tr>
								<th>부서번호</th>
								<td><input type="text" name=deptNo" class="form-control"
									value="${vo.deptNo}" disabled />
									<input type="hidden" name="deptNo" value="${vo.deptNo}" />
									<input type="hidden" name="cmd" value="modifyDeptOk" /></td>
							</tr>

							<tr>
								<th>부서명</th>
								<td><input type="text" name="deptName" class="form-control"
									value="${vo.deptName}" /></td>
							</tr>

							<tr>
								<td colspan="2"><a href="admin?cmd=selectPos"
									class="btn btn-outline-primary">목록</a> <input type="submit"
									class="btn btn-outline-success" value="수정" /></td>
							</tr>

						</table>

					</form>
				</div>
			</div>

			<jsp:include page="/view/comm/footer.jsp"></jsp:include>
		</div>

		<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
</body>
</html>