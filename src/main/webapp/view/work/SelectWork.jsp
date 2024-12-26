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
								<!-- startWorkBtn : 출근 버튼, endWorkBtn : 퇴근 버튼 -->
								<button type="button" id="startWorkBtn" class="btn pd-setting">출근</button>
								<button type="button" id="endWorkBtn" class="btn pd-setting">퇴근</button>

							</div>

							<!-- 	<div>
								<button id="showDateBtn">현재 날짜 및 시간 출력(테스트 대충 만듬)</button>
							</div> -->

							<div class="asset-inner">
								<table id="table">
									<tr>
										<th class="text-center col-sm-2">사원명</th>
										<th class="text-center col-sm-2">부서</th>
										<th class="text-center col-sm-2">직급</th>
										<th class="text-center col-sm-2">출근시간</th>
										<th class="text-center col-sm-2">퇴근시간</th>
									</tr>


									<c:forEach var="vo" items="${list}">
										<tr>
											<td class="text-center col-sm-2">${vo.empNo}</td>
											<td class="text-center col-sm-2">${vo.empName}</td>
											<td class="text-center col-sm-2">${vo.deptName}</td>
											<td class="text-center col-sm-2">${vo.posName}</td>
											<td class="text-center col-sm-2">${vo.startworkTime}</td>
											<td class="text-center col-sm-2">${vo.endworkTime}</td>
										</tr>
									</c:forEach>


								</table>
							</div>

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
	document.getElementById("startWorkBtn").addEventListener("click", function() {

		// 현재 시간 가져오기
		// setTimeout을 사용해서 1초 후에 날짜와 시간 출력
		// setTimeout() : 지연 시간을 설정할 수 있음
		// new Date() : 현재 날짜와 시간을 가져옴
		// .toLocaleString() : 날짜와 시간을 사용자의 로컬 형식으로 변환해주는 함수
		setTimeout(function() {
			const now = new Date(); 				// 현재 날짜와 시간
			const startTime = now.toLocaleString(); // 현재 날짜와 시간을 로컬 형식으로 변환
									
			// 테이블에 새로운 행 추가
			const table = document.getElementById("table");		// 테이블 요소 가져오기
			const newRow = table.insertRow();					// 새로운 행 추가
						
			// 출근 시간 행 추가
			const newCell = newRow.insertCell();				// 새로운 셀 추가
			newCell.innerText = '출근 시간 : ' + startTime;		// 출근 시간 삽입
									
			// 출근 버튼 클릭하면 동작 (알림 팝업창 생성)
			alert("출근 시간이 정상 등록되었습니다.");
		},1000);	// 1초 후에 실행
	});
				
	// 퇴근 버튼을 클릭했을 때 실행할 함수 설정
	document.getElementById("endWorkBtn").addEventListener("click", function(){
		
		// 현재 시간을 가져오기
		const now = new Date();								// 현재 날짜와 시간
		const endTime = now.toLocaleString();		// 로컬 형식으로 변환
		
		// 테이블에 새로운 행 추가
		const table = document.getElementById("table");		// 테이블 요소 가져오기
		const newRow = table.insertRow();					// 새로운 행 추가
		
		// 퇴근 시간 행 추가
		const newCell = newRow.insertCell();				// 새로운 셀 추가
		newCell.innerText = '퇴근 시간 : ' + endTime;			// 행에 퇴근 시간 삽입
		
		// 퇴근 버튼 클릭하면 동작 (알림 팝업창 생성)
		alert("퇴근 시간이 정상 등록되었습니다.");
	},1000);	// 1초 후에 실행
	
</script>

</body>
</html>