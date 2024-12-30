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
					`

					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="product-status-wrap aura_content">


							<%-- 검색 부분 --%>
						<form action="selectWork" method="get">
							<c:if test="${loginEmp.empNo eq '2024000' }">
								<div class="text-right mg-bt-10">
									<div class="form-inline">
										<div class="form-group">
											<label for="exampleInputEmail2">사원번호</label> <input type="email" class="form-control mg-wd-10" id="empNo" placeholder="사원번호 7자리">
										</div>
										<div class="form-group">
											<label for="exampleInputName2">사원명</label> <input type="text" class="form-control mg-wd-10" id="empName" placeholder="사원 이름">
										</div>
										<div class="form-group">
										  	<label>날짜</label>
	                                        <%-- <input type="text" name="hiredate" value="" /> --%>
	                                        <input type="date" name="attenDate" class="form-control mg-wd-10" id="attenDate" required pattern="\d{4}-\d{2}-\d{2}" />
										  </div>
										<span class="pd-lt-10">
											<button type="button" class="btn pd-setting" id="loadBtn">조회</button>
											<!-- btn-primary -> pd-setting -->
										</span>
									</div>

								</div>
							</c:if>
						</form>


							<%-- 사용자들이 이용하는 출퇴근 버튼 부분 (관리자는 볼 필요 없음) --%>
							<c:if test="${loginEmp.empNo ne '2024000' }">
								<div class="text-right mg-bt-10">
									<!-- startWorkBtn : 출근 버튼, endWorkBtn : 퇴근 버튼 -->
									<button type="button" id="startWorkBtn" class="btn pd-setting">출근</button>
									<button type="button" id="endWorkBtn" class="btn pd-setting">퇴근</button>

								</div>
							</c:if>

							<!--    <div>
                        <button id="showDateBtn">현재 날짜 및 시간 출력(테스트 대충 만듬)</button>
                     </div> -->

							<div class="asset-inner">
								<table id="table">
									<tr>
										<th class="text-center col-sm-2">사원번호</th>
										<th class="text-center col-sm-2">사원명</th>
										<th class="text-center col-sm-2">부서</th>
										<th class="text-center col-sm-2">직급</th>
										<th class="text-center col-sm-2">출근시간</th>
										<th class="text-center col-sm-2">퇴근시간</th>
									</tr>

									<%-- 
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
                               --%>
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
     
      $( ()=> {
    	    // 페이지 로딩 시, loadWork() 실행 (자동으로 페이지가 보여짐)
    	    loadWork();

    	    // 조회 버튼 클릭 시, loadWork() 실행 : 조회 버튼 클릭 시 실행하려면 클릭 이벤트 리스너 안에 넣어야 함
    	    $("#loadBtn").on("click", ()=> {
    	        loadWork(); // 조회 버튼 클릭 시, loadWork() 실행
    	    });
    	});
      
      function loadWork(){
    	  
    	  let empNo = $("#empNo").val();			// 입력한 사원번호
    	  let empName = $("#empName").val();		// 입력한 사원명
    	  let attenDate = $("#attenDate").val();	// 입력한 등록일자
    	 
    	  console.log("empNo : " + empNo);
    	  console.log("empName : " + empName);
    	  console.log("attenDate : " + attenDate);
    	  
    	$.ajax({
    		url: "workasync",						// 서버 url
    		type: "GET",
    		data: {
    			cmd: "selectWorkAsync",				// 서버로 보낼 데이터 (조회 조건)
    			empNo: empNo,
    			empName: empName,
    			attenDate: attenDate
    		},
    		dataType: 'json',						// JSON 형식으로 응답 받기
    		success: (data) => {
    			console.log("data = ", data);
    			let workList = data.workList;

    	            // 테이블 초기화
    	            $("tr[name='workList']").remove(); // 기존의 데이터 지우기 .empty();
/* 
	
    	});
 	  
        $.ajax({
            url:"workasync", 
            type: "post",
           data: {cmd:"selectWorkAsync"}, // json 방식으로 서블릿에 보낼 데이터
           dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
              success: (data) => {
            	  console.log("data = ", data);
                 let workList = data.workList;
              
                 $("tr[name='workList']").remove(); // .empty(); */
              
	             $.each(workList, (idx, row) => {
	            	 console.log("workList = ", workList);
	                 let appendText = ""; // style='height: 415px; vertical-align: top;'
	                 appendText = "<tr name='workList' style='height: 69.1px;'>"; //  style='height: 47px;'
	                 
	                 appendText +="<td><a>"+ row.empNo +"</a></td>"
	                          +"<td><a>"+ row.empName +"</a></td>"
	                          +"<td><a>"+ (row.deptName == null || row.deptName == "" || row.deptName == "undefined" ? "" : row.deptName ) +"</a></td>"
	                          +"<td><a>"+ (row.posName == null || row.posName == "" || row.posName == "undefined" ? "" : row.posName) +"</a></td>"
	                          +"<td><a>"+ row.startworkTime +"</a></td>";
	                 
	                 // 퇴근시간
	                 appendText +="<td><a>"+ 
	                  (row.endworkTime == null || row.endworkTime == "" || row.endworkTime == "undefined" ? "근무중" : row.endworkTime) +"</a></td>";
	                     
	               appendText +="</tr>";
	                 
	                 $("#table").append(appendText);
	             });
              },
              error:function(request, err) {
                 console.log("error");
              }
          });
        
     } // end loadWork
     

   
      // 출근 버튼 클릭 시 출근 시간 조회
      $("#startWorkBtn").on("click", function() {
         let empNo = $("#empNo").val(); // 사원 번호 가져오기
         console.log("empno : " + empNo);
         $.ajax({
            url: 'workservlet', // 출근 시간을 처리할 서블릿 경로
            type: 'POST',
            dataType : 'json', 
            data: { empNo: empNo, workGubun:"start" },
            success: function(response) {
               // 서버로부터 받은 출근 시간 표시
               if (response.status) {
                  alert('출근 시간이 등록되었습니다: ');
                  loadWork();
               } else {
                  alert('출근 처리가 되어있는 상태입니다.');
               }
            },
            error: function() {
               alert('출근 시간 처리 중 오류가 발생했습니다.');
            }
         });
      });

      // 퇴근 버튼 클릭 시 퇴근 시간 조회
      $("#endWorkBtn").on("click", function() {
         var empNo = $("#empNo").val(); // 사원 번호 가져오기

         $.ajax({
            url: 'workservlet', // 퇴근 시간을 처리할 서블릿 경로
            type: 'POST',
            dataType : 'json', 
            data: { empNo: empNo, workGubun:"end"},
            success: function(response) {
               // 응답 처리 후 퇴근 시간 표시
               if (response.status) {
                  alert('퇴근 시간이 등록되었습니다: ');
                  loadWork();
               } else {
                  alert('퇴근 처리가 되어있는 상태입니다.');
               }
            },
            error: function() {
               alert('퇴근 시간 처리 중 오류가 발생했습니다.');
            }
         });
      });
      
      
   </script>

</body>
</html>