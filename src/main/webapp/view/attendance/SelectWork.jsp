<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>

<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<script>
$(()=>{
	
   $('#modifyPosModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        var name = button.data('name');

        var modal = $(this);
        modal.find('#posNo').val(id);
        modal.find('#posName').val(name);
      });
     
})
</script>

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
								<button type="button" id="insertPosAdd" class="btn pd-setting" data-toggle="modal" data-target=".insert-pos-modal">출근</button>
								<button type="button" id="insertPosAdd" class="btn pd-setting" data-toggle="modal" data-target=".insert-pos-modal">퇴근</button>
							</div>

							<div class="asset-inner">
								<table>
									<tr>
										<th class="text-center col-sm-2">사원명</th>
										<th class="text-center col-sm-4">직급명</th>
										<th class="text-center col-sm-2">출근시간</th>
										<th class="text-center col-sm-2">퇴근시간</th>
									</tr>

									<c:forEach var="vo" items="${list}">
										<tr>
											<td class="text-center">${vo.posNo}</td>
											<td class="text-center">${vo.posName}</td>
											<td class="text-center">
												<span class="tooltip-wrapper" data-toggle="tooltip" title="수정">
													<button type="button" class="btn pd-setting-ed modifyBtn" data-original-title="수정" data-toggle="modal" data-target="#modifyPosModal" data-id="${vo.posNo}" data-name="${vo.posName}">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
												</span>
												<span class="tooltip-wrapper" data-toggle="tooltip" title="삭제">
														<button type="button" id="deleteBtn" class="btn pd-setting-ed" onclick='window.location.href="admin?cmd=deletePos&posNo=${vo.posNo}"'>
															<i class="fa fa-trash-o" aria-hidden="true"></i>
														</button>
												</span>
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
			</div>
		</div>
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<!-- 직급 등록 모달 -->
	<div class="modal fade insert-pos-modal" id="insertPosModal"
		tabindex="-1" role="dialog" aria-labelledby="insertPosModalLabel">
		<!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
			<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="insertPosModalLabel">직급 등록</h4>
				</div>
				
				<div class="modal-body">
					<div class="product-status-wrap">
						<div class="asset-inner">
							<form action="admin">
								<table id="insertPos">
									<tr>
										<th class="text-center col-sm-2">직급번호</th>
										<td>
										<input type="number" name="posNo" class="form-control" required /> <input type="hidden" name="cmd" value="insertPosOk" />
										</td>
									</tr>
									<tr>
										<th class="text-center col-sm-4">직급명</th>
										<td>
											<input type="text" name="posName" class="form-control" required />
										</td>
									</tr>
									<tr>
										<td colspan="2" class="text-end">
										 	<input type="submit" value="등록" class="btn btn-primary pd-setting" />
										 </td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 직급 수정 모달 -->
	<div class="modal fade modify-pos-modal" id="modifyPosModal"
		tabindex="-1" role="dialog" aria-labelledby="modifyPosModalLabel">
		<div class="modal-dialog modal-lg middleMoalWd">
			<div class="modal-content">
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modifyPosModalLabel">직급 수정</h4>
				</div>
				
				<div class="modal-body">
					<div class="product-status-wrap">
						<div class="asset-inner">
							<form action="admin">
								<table id="modifyPos">
									<tr>
										<th class="text-center col-sm-2">직급번호</th>
										<td>
											<input type="number" name="posNo" id="posNo" class="form-control" required />
											<input type="hidden" name="cmd" value="modifyPosOk" />
										</td>
									</tr>
									<tr>
										<th class="text-center col-sm-4">직급명</th>
										<td>
											<input type="text" name="posName" id="posName" class="form-control" required />
										</td>
									</tr>
									<tr>
										<td colspan="2" class="text-end"><input type="submit" value="저장" class="btn btn-primary pd-setting" />
											<a href="admin?cmd=selectPos">
												<input type="button" value="취소" class="btn btn-primary pd-setting" />
											</a>
										</td>
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