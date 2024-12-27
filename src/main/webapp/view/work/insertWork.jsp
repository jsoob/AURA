<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>출퇴근 관리</title>

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
								<!-- startWorkBtn : 출근 버튼, endWorkBtn : 퇴근 버튼 -->
								<button type="button" id="startWorkBtn" class="btn pd-setting">출근</button>
								<button type="button" id="endWorkBtn" class="btn pd-setting">퇴근</button>

							</div>

							<!-- 출퇴근 기록 입력 폼 -->
							<!-- POST : 폼 데이터를 서버로 전송할 때 사용할 HTTP 메서드를 지정
										데이터를 서버에 안전하게 보낼 때 사용하는 방법 -->
							<form id="workForm" action="InsertWorkAction" method="POST">
								<input type="hidden" id="empNo" name="empNo" value="${vo.empNo}" />
								<table id="table">
									<thead>
										<tr>
											<th class="text-center col-sm-2">사원명</th>
											<th class="text-center col-sm-2">부서</th>
											<th class="text-center col-sm-2">직급</th>
											<th class="text-center col-sm-2">출근시간</th>
											<th class="text-center col-sm-2">퇴근시간</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vo" items="${list}">
											<tr>
												<td class="text-center col-sm-2">${vo.attenDate}</td>
												<td class="text-center col-sm-2">${vo.empNo}</td>
												<%-- <td class="text-center col-sm-2">${vo.deptName}</td>
												<td class="text-center col-sm-2">${vo.posName}</td> --%>
												<td class="text-center col-sm-2">${vo.startworkTime}</td>
												<td class="text-center col-sm-2">${vo.endworkTime}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
										<tr>
											<!-- hidden : 서버로 값을 전송할 수 있게 함. 출근 및 퇴근 버튼 클릭 시, 해당 필드에 값을 설정하고 폼을 전송하는 역할
														  페이지에서는 보이지 않지만, 값을 폼에 포함시켜 서버로 전송 (readonly보단 hidden이 더 좋다)
											 -->
											<td><input type="hidden" id="startworkTime" name="startworkTime" /></td>
											<td><input type="hidden" id="endworkTime" name="endworkTime" /></td>
										</tr>
								

								<%-- <input type="hidden" name="empNo" value="${empNo}" /> --%>
							</form>


						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>



	<script>
		//출근 버튼을 클릭했을 때 실행할 함수 설정
		// document.getElementById("startWorkBtn").addEventListener("click", function() {
			$("#startWorkBtn").on("click", function() {
			var empNo = $("#empNo").val();  // 사원 번호 가져오기	
			console.log("empNo 값 ", empNo);		// empNo 값 출력 (브라우저 콘솔 확인)
			
			
				$.ajax({
						
//					url: "/aura/startwork",		// 서버에 보낼 URL
					url: '/aura/StartWorkServlet',		// 서버에 보낼 URL
					type : 'POST',				// 요청 방식 (POST)
					data: {
//						no: "${vo.empNo}"		// 사원 번호 전달 (JSP 표현식)
						empNo: empNo
					}, 
					success : function(data){
					    console.log(data);  // 서버 응답 확인용 (디버깅용)
					    // 서버로부터 받은 출근시간 표시
					    if (data.success) {  // 수정: response -> data
					        alert('출근 시간이 정상 등록되었습니다.' + data.startworkTime);  // 수정: response -> data
					    } else {
					        alert('출근 시간 등록에 실패했습니다.');
					    }
					},

						/* // 현재 시간 가져오기
						const now = new Date(); // 현재 날짜와 시간
						const startTime = now.toLocaleString(); // 현재 날짜와 시간을 로컬 형식으로 변환

						// startWorkTime 필드에 값 설정
						document.getElementById("startworkTime").value = startTime;
							
						// 폼 제출
						document.getElementById("workForm").submit();
					},
 */						
					error: function(xhr, status, error){
						console.error("출근 처리 오류 :", error);						// 오류 내용 출력
						alert("출근 시간 등록 중 오류가 발생하였습니다. 관리자에게 문의하세요.");		// 실패 메시지
														
					}
				});
		});
				
						
		// 퇴근 버튼을 클릭했을 때 실행할 함수 설정
//		document.getElementById("endWorkBtn").addEventListener("click", function() {
		$("#startWorkBtn").on("click", function() {
	
				var empNo = $("#empNo").val();	// 사원 번호 가져오기
				
				$.ajax({
				
//				url: "/aura/endwork",		// 서버에 보낼 URL
				url: "/aura/EndWorkServlet",		// 서버에 보낼 URL
				type : "POST",				// 요청 방식 (POST)
				data: {
//					no: "${vo.empNo}"		// 사원 번호 전달 (JSP 표현식)
					empNo: empNo
				}, 
				success : function(data){
					console.log(data);		// 서버 응답 확인용 (디버깅용)
				alert("퇴근 시간이 정상 등록 되었습니다. ");	// 성공메시지 출력

					// 현재 시간을 가져오기
					const now = new Date(); // 현재 날짜와 시간
					const endTime = now.toLocaleString(); // 로컬 형식으로 변환

					// endworkTime 필드에 값 설정
					document.getElementById("endworkTime").value = endTime;
					
					// 폼 제출 : 폼을 자동으로 제출하는 역할 (서버로 데이터를 보냄)
					// submit() : html 폼을 제출하는 javaScript 메서드
					//			  출근 버튼을 클릭하면 출근 시간을 서버에 저장하려고 할 때, submit() 메서드를 사용해서 폼을 서버로 전송
					document.getElementById("workForm").submit();

				},
					
					error: function(xhr, status, error){
						console.error("퇴근 처리 오류 :", error);						// 오류 내용 출력
						alert("퇴근 시간 등록 중 오류가 발생하였습니다. 관리자에게 문의하세요.");		// 실패 메시지
														
					}
				
				});
		});
	</script>

</body>
</html>
