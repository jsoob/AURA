<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<script>
	$(()=>{
		$('.mypage-modal').on('show.bs.modal', function (event) {
			$("#myModifyBtn").css("display", "");
			$("#mySaveBtn").css("display", "none");
			$(".myImageBtnDiv").css("display", "none");
			
			let myModal = $(this);
			myModal.find('[name=empImage]').attr("src", "${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}");
			myModal.find('[name=empNo]').val("${loginEmp.empNo }").attr("disabled", true);
			myModal.find('[name=birthdate]').val("${loginEmp.birthdate }").attr("disabled", true);
			
			myModal.find('[name=hiredate]').val("${loginEmp.hiredate }").attr("disabled", true);
			myModal.find('[name=quitdate]').val("${loginEmp.quitdate }").attr("disabled", true);
			    
			myModal.find('[name=empName]').val("${loginEmp.empName }").attr("disabled", true);
			myModal.find('[name=cellphone]').val("${loginEmp.cellphone }").attr("disabled", true);
			    
			myModal.find('[name=deptName]').val("${loginEmp.deptName }").attr("disabled", true);
			myModal.find('[name=posName]').val("${loginEmp.posName }").attr("disabled", true);
			    
			myModal.find('[name=empEmail]').val("${loginEmp.empEmail }").attr("disabled", true);
			myModal.find('[name=cmpEmail]').val("${loginEmp.cmpEmail }").attr("disabled", true);
			 
		});
		
		$("#myModifyBtn").on("click", ()=> {
			$("#myModifyBtn").css("display", "none");
			$("#mySaveBtn").css("display", "");
			$(".myImageBtnDiv").css("display", "");
			
			let myModal = $("#myModal");
			// myModal.find('[name=empImage]').attr("src", "${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}");
			myModal.find('[name=empNo]').attr("disabled", true);
			myModal.find('[name=birthdate]').attr("disabled", false);
			
			myModal.find('[name=hiredate]').attr("disabled", true);
			myModal.find('[name=quitdate]').attr("disabled", true);
			    
			myModal.find('[name=empName]').attr("disabled", true);
			
			myModal.find('[name=cellphone]').attr("disabled", false);
			    
			myModal.find('[name=deptName]').attr("disabled", true);
			myModal.find('[name=posName]').attr("disabled", true);
			    
			myModal.find('[name=empEmail]').attr("disabled", false);
			myModal.find('[name=cmpEmail]').attr("disabled", true);
		});
		
		$("#myDelImg").on("click", ()=> {
			myRemoveImage();
		});
		
		$("#mySaveBtn").on("click", ()=> {
			console.log("save");
			
			// 방법 1
			/* 
			// let fileData = new FormData($("#myModal")[0]);
			let myModal = $("#myModal");
			console.log("myModal = ", myModal);
			
			let myFormData = new FormData();
			myFormData.append("cmd", "editMyEmpOk");
			// myFormData.append("file", $("#myChangeImg")[0].files[0]);
			myFormData.append("myChangeImg", myModal.find('[name=myChangeImg]')[0].files[0]);
			myFormData.append("empNo", myModal.find('[name=empNo]').val());
			myFormData.append("birthdate", myModal.find('[name=birthdate]').val());
			myFormData.append("cellphone", myModal.find('[name=cellphone]').val());
			myFormData.append("empEmail", myModal.find('[name=empEmail]').val());
			console.log( myFormData);
			 */
			 
			// 방법 2
			/*  
			let myFormData = $("form[name=myPageForm]").serialize();
			myFormData += "&cmd="+ "editMyEmpOk";
			 */
			
			let myModal = $("#myModal");
			
			let myFormData = new FormData();
			myFormData.append("cmd", "editMyEmpOk");
			// myFormData.append("file", $("#myChangeImg")[0].files[0]);
			myFormData.append("myChangeImg", myModal.find('[name=myChangeImg]')[0].files[0]);
			myFormData.append("empNo", myModal.find('[name=empNo]').val());
			myFormData.append("birthdate", myModal.find('[name=birthdate]').val());
			myFormData.append("cellphone", myModal.find('[name=cellphone]').val());
			myFormData.append("empEmail", myModal.find('[name=empEmail]').val());
			console.log( myFormData); 
			
			$.ajax({
	        	url:"main", // upload 
	        	type: "post", 
	            enctype: 'multipart/form-data',
	            
	            // AJAX 통신 시 contentType, processData 파라미터를 false 로 지정하여 통신 시 오류 없음.
	            contentType: false, 
	            processData: false, 
	            
	            data: myFormData, 
				dataType: 'json', // json 타입으로 풀어줌
	            success: (data) => {
	            	//$('#headerEmpImg').attr("src", "${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}");
	            	//$("#myModal").modal('hide');
	            	location.reload(true);
	            },
	            error:function(request, err) {
	            	console.log("error");
	            }
	        });
		});
	})
	
	// 사진 업로드
	function myLoadFile(input) {
	    let file = input.files[0];	//선택된 파일 가져오기
		
	  	//새로운 이미지 추가
	    let myModal = $("#myModal");
		myModal.find('[name=empImage]').attr("src", URL.createObjectURL(file));
	}

	function myRemoveImage() {
		let myModal = $("#myModal");
		myModal.find('[name=empImage]').attr('src', '');
	}
</script>
    
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="logo-pro">

				<a href="${pageContext.request.contextPath}/main"><img class="main-logo" src="${pageContext.request.contextPath}/img/logo/AURAlogo.png" alt="" style="width: 200px; height: 60px;" /></a>
			</div>
		</div>
	</div>
</div>
            
<div class="header-advance-area">
    <div class="header-top-area">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="header-top-wraper">
                        <div class="row">
                            <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
                                <div class="menu-switcher-pro">
                                    <button type="button" id="sidebarCollapse" class="btn bar-button-pro header-drl-controller-btn btn-info navbar-btn">
										<i class="fa fa-align-justify"></i>
										<!-- "educate-icon educate-nav"  -->
									</button>
                                </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-7 col-sm-6 col-xs-12">
                                <div class="header-top-menu tabl-d-n">
                                   <%-- 관리자에서만 보여주는 메뉴 --%>
                                    <c:if test="${loginEmp.empNo eq '2024000' }">
	                                    <ul class="nav navbar-nav mai-top-nav">
	                                        <%-- <li class="nav-item"><a href="${pageContext.request.contextPath}/admin?cmd=adminLoad" class="nav-link">관리자 조회</a>
	                                        </li> --%>
	                                        
	                                        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin?cmd=selectDept" class="nav-link">부서관리</a>
	                                        </li>
	                                        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin?cmd=selectPos" class="nav-link">직급관리</a>
	                                        </li>
	                                        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin?cmd=selectEmp" class="nav-link">사원관리</a>
	                                         </li>
	                                         <li class="nav-item"><a href="${pageContext.request.contextPath}/work?cmd=selectWork" class="nav-link">근태관리</a>
	                                         </li>
	                                        <!-- <li class="nav-item dropdown res-dis-nn">
	                                            <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">Project <span class="angle-down-topmenu"><i class="fa fa-angle-down"></i></span></a>
	                                            <div role="menu" class="dropdown-menu animated zoomIn">
	                                                <a href="#" class="dropdown-item">Documentation</a>
	                                                <a href="#" class="dropdown-item">Expert Backend</a>
	                                                <a href="#" class="dropdown-item">Expert FrontEnd</a>
	                                                <a href="#" class="dropdown-item">Contact Support</a>
	                                            </div>
	                                        </li> -->
	                                    </ul>
                                    </c:if>
                                   
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                                <div class="header-right-info">
                                    <ul class="nav navbar-nav mai-top-nav header-right-menu">
                                        
                                        <!-- Message 알림창 dropdown -->
                                        <li class="nav-item dropdown">
                                            <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
	                                            <i class="fa fa-envelope-o" aria-hidden="true"></i> <%-- educate-icon educate-message edu-chat-pro --%>
	                                            <span class="indicator-ms"></span>
                                            </a>
                                            <div role="menu" class="author-message-top dropdown-menu animated zoomIn">
                                                <div class="message-single-top">
                                                    <h1>Message</h1>
                                                </div>
                                                <ul class="message-menu">
                                                    <li>
                                                        <a href="#">
                                                            <div class="message-img">
                                                                <img src="${pageContext.request.contextPath}/img/contact/1.jpg" alt="">
                                                            </div>
                                                            <div class="message-content">
                                                                <span class="message-date">16 Sept</span>
                                                                <h2>Advanda Cro</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="message-img">
                                                                <img src="${pageContext.request.contextPath}/img/contact/4.jpg" alt="">
                                                            </div>
                                                            <div class="message-content">
                                                                <span class="message-date">16 Sept</span>
                                                                <h2>Sulaiman din</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="message-img">
                                                                <img src="${pageContext.request.contextPath}/img/contact/3.jpg" alt="">
                                                            </div>
                                                            <div class="message-content">
                                                                <span class="message-date">16 Sept</span>
                                                                <h2>Victor Jara</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="message-img">
                                                                <img src="${pageContext.request.contextPath}/img/contact/2.jpg" alt="">
                                                            </div>
                                                            <div class="message-content">
                                                                <span class="message-date">16 Sept</span>
                                                                <h2>Victor Jara</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                </ul>
                                                <div class="message-view">
                                                    <a href="#">View All Messages</a>
                                                </div>
                                            </div>
                                        </li>
                                        
                                        <!-- 사원정보 옆에 Notifications 알림창 부분 -->
                                        <li class="nav-item">
                                        	<a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
	                                        	<i class="fa fa-bell-o" aria-hidden="true"></i> <%-- educate-icon educate-bell --%>
	                                        	<span class="indicator-nt"></span>
                                        	</a>
                                            <div role="menu" class="notification-author dropdown-menu animated zoomIn">
                                                <div class="notification-single-top">
                                                    <h1>Notifications</h1>
                                                </div>
                                                <ul class="notification-menu">
                                                    <li>
                                                        <a href="#">
                                                            <div class="notification-icon">
                                                                <i class="educate-icon educate-checked edu-checked-pro admin-check-pro" aria-hidden="true"></i>
                                                            </div>
                                                            <div class="notification-content">
                                                                <span class="notification-date">16 Sept</span>
                                                                <h2>Advanda Cro</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="notification-icon">
                                                                <i class="fa fa-cloud edu-cloud-computing-down" aria-hidden="true"></i>
                                                            </div>
                                                            <div class="notification-content">
                                                                <span class="notification-date">16 Sept</span>
                                                                <h2>Sulaiman din</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="notification-icon">
                                                                <i class="fa fa-eraser edu-shield" aria-hidden="true"></i>
                                                            </div>
                                                            <div class="notification-content">
                                                                <span class="notification-date">16 Sept</span>
                                                                <h2>Victor Jara</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="notification-icon">
                                                                <i class="fa fa-line-chart edu-analytics-arrow" aria-hidden="true"></i>
                                                            </div>
                                                            <div class="notification-content">
                                                                <span class="notification-date">16 Sept</span>
                                                                <h2>Victor Jara</h2>
                                                                <p>Please done this project as soon possible.</p>
                                                            </div>
                                                        </a>
                                                    </li>
                                                </ul>
                                                <div class="notification-view">
                                                    <a href="#">View All Notification</a>
                                                </div>
                                            </div>
                                        </li>
                                        
                                        <!-- 사원 정보 표시 부분 -->
                                        <li class="nav-item">
                                            <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
												<%-- <img src="${pageContext.request.contextPath}/img/product/pro4.jpg" alt="" /> --%>
												<img id="headerEmpImg" alt="ㅣ" src="${ loginEmp.empImage != null ? pageContext.request.contextPath : '' }${loginEmp.empImage}" />
												
												<span class="admin-name">${loginEmp.empName }</span>
												<i class="fa fa-angle-down edu-icon edu-down-arrow"></i>
											</a>
                                            <ul role="menu" class="dropdown-header-top author-log dropdown-menu animated zoomIn">
                                                <!-- <li><a href="#"><span class="edu-icon edu-home-admin author-log-ic"></span>My Account</a></li> -->
                                                <li>
                                                	<span class="header-menu-list" data-toggle="modal" data-target=".mypage-modal">My Page</span>
                                                	<%-- 
                                                	<a href="${pageContext.request.contextPath}/mypage?cmd=detail" >
                                                		<span class="edu-icon edu-user-rounded author-log-ic"></span>My Page</a>
                                                	 --%>
                                                </li>
                                                <!-- <li><a href="#"><span class="edu-icon edu-money author-log-ic"></span>User Billing</a></li> -->
                                                <!-- <li><a href="#"><span class="edu-icon edu-settings author-log-ic"></span>Settings</a></li> -->
                                                <li><a href="${pageContext.request.contextPath}/login?cmd=logout"><span class="edu-icon edu-locked author-log-ic"></span>Log Out</a>
                                                </li>
                                            </ul>
                                        </li>
                                        
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%-- modal --%>
    <div class="modal fade mypage-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" > <!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg myMoalWd">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="myModalLabel">&nbsp;
						<div class="col-lg-8 col-sm-8 col-xs-8 text-left">
							내 정보
						</div>
						
						<div class="col-lg-3 col-sm-3 col-xs-3 pd-nn text-right">
							<button type="button" id="myModifyBtn" class="btn btn-primary">수정</button>
							<button type="button" id="mySaveBtn" class="btn btn-primary">저장</button>
						</div>
					</h4>
	    		</div>
	    		<div class="modal-body">
	    			
	    			<div class="row">
	    				<form name="myPageForm">
		    				<div class="col-lg-12 col-sm-12 col-xs-12">
								<div class="col-lg-12 col-sm-12 col-xs-12 myImageDiv text-center">
		                        	<div class="col-lg-12 mg-bt-10" style="height: 200px;">
		                        		<img class="myViewImg" name="empImage" alt="사원이미지 없음" src="">
		                        	</div>
		                        		<div class="col-lg-12 myImageBtnDiv mg-bt-10">
		                            		<label class="btn btn-success" style="line-height: 26px;" for="myChangeImg">
												<i class="fa fa-exchange" aria-hidden="true"></i> 사진 변경
											</label>
											<input type="file" id="myChangeImg" name="myChangeImg" style="display:none;" accept="image/*" onchange="myLoadFile(this)">
		                            		<button type="button" id="myDelImg" class="btn btn-danger bg-red" style="line-height: 26px;">
		                            			<i class="fa fa-times" aria-hidden="true"></i> 사진 삭제</button>
	                                	</div>
		                        </div>
								
								<div class="col-lg-12 col-sm-12 col-xs-12">
									<div class="form-group-inner mg-bt-20">
			                        	<div class="row">
			                        		<div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                	<label class="login2 pull-left pull-left-pro">사원번호</label>
			                                </div>
			                                <div class="col-lg-4 col-md-4 col-sm-9 col-xs-12">
			                                	<input type="text" name="empNo" class="form-control" disabled="disabled">
			                                </div>
			                                        
			                                <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                	<label class="login2 pull-left pull-left-pro">생일</label>
			                                </div>
			                                <div class="col-lg-4 col-md-4 col-sm-9 col-xs-12">
			                                	<input type="date" name="birthdate" class="form-control" pattern="\d{4}-\d{2}-\d{2}" disabled="disabled" />
		                                    </div>
			                        	</div>
			                        </div>
			                        
			                        <div class="form-group-inner mg-bt-20">
			                       		<div class="row">
			                       			<div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">입사일자</label>
			                                </div>
			                            	<div class="col-lg-4 col-md-4 col-sm-9 col-xs-12">
			                            		<input type="date" name="hiredate" class="form-control" pattern="\d{4}-\d{2}-\d{2}" disabled="disabled" />
		                                    </div>
			                                <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                	<label class="login2 pull-left pull-left-pro">퇴사일자</label>
			                                </div>
			                                <div class="col-lg-4 col-md-4 col-sm-9 col-xs-12">
			                                	<input type="date" name="quitdate" class="form-control" pattern="\d{4}-\d{2}-\d{2}" disabled="disabled" />
		                                    </div>
			                            </div>
			                       </div> 
			                       
			                        <div class="form-group-inner mg-bt-20">
			                        	<div class="row">
			                        		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">사원명</label>
			                        		</div>
				                        	<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
				                        		<input type="text" name="empName" class="form-control" disabled="disabled">
				                        	</div>
				                        	<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">휴대폰</label>
			                            	</div>
			                            	<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
			                            		<input type="text" name="cellphone" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"
			                            			maxlength="11" class="form-control" disabled="disabled" />
			                            	</div>
			                        	</div>
			                        </div>
			                        <div class="form-group-inner mg-bt-20">
			                        	<div class="row">
			                        		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">부서</label>
			                        		</div>
			                        		<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
			                                	<input type="text" name="deptName" class="form-control" disabled="disabled">
			                            	</div>
			                            	
			                            	<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">직급</label>
			                        		</div>
			                        		<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
			                        			<input type="text" name="posName" class="form-control" disabled="disabled">
			                        		</div>
			                            </div>
			                        </div>
			                        
			                        <div class="form-group-inner mg-bt-20">
			                        	<div class="row">
			                        		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">외부 이메일</label>
			                        		</div>
			                        		<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
			                        			<input type="text" name="empEmail" class="form-control" disabled="disabled" maxlength="100">
			                        		</div>
			                        		<div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                        			<label class="login2 pull-left pull-left-pro">사내 이메일</label>
			                        		</div>
			                        		<div class="col-lg-4 col-md-9 col-sm-9 col-xs-12">
			                        			<input type="text" name="cmpEmail" class="form-control" disabled="disabled">
			                        		</div>
			                        	</div>
			                       </div>
			                             
		                        </div>
		                        
		                    </div>
	    				</form>
	    			</div>
	    			
	    		</div><!-- modal-body -->
			</div>
		</div>
	</div>
    
    <!-- Mobile Menu start -->
    <!-- mobile로 볼때? 굳이? -->    
    <!-- Mobile Menu end -->
    
    <div class="breadcome-area">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="breadcome-list single-page-breadcome" style="margin-bottom: 20px;">
                        <div class="row">
                        	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        														<%-- ${ pages } => ${commAt["pages"]} HashMap 타입 변경 --%>
                                <span style="font-weight: 700; font-size: 2em;">${commAt["pagesName"]}</span>
                            </div>
                            
                        	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <ul class="breadcome-menu">
                                	<%-- /aura(OK)/admin?cmd=(OK) --%>
                                	<%-- getRequestURL() : 프로토콜+도메인+포트번호+컨텍스트 경로+서블릿 경로 --%>
                                	<%--
                                		// 잠시 출력용
                                		System.out.println("request.getRequestURI() = " + request.getRequestURI());   
                                		System.out.println("request.getContextPath() = " + request.getContextPath());   
                                		System.out.println("request.getRequestURL() = " + request.getRequestURL());   
                                		System.out.println("request.getServletPath() = " + request.getServletPath());   
                                		/* 
                                		request.getRequestURI(); //프로젝트경로부터 파일까지의 경로값을 얻어옴 (/test/index.jsp)
										request.getContextPath();  //프로젝트의 경로값만 가져옴(/test)
										request.getRequestURL();   // 전체 경로를 가져옴 (http://localhost:8080/test/index.jsp)
										request.getServletPath();  //파일명 (/index.jsp)
										 */
										 
										 getURL = http://localhost:8080/aura/admin
										request.getRequestURI() = /aura/view/admin/adminLoad.jsp
										request.getContextPath() = /aura vb6f 
										request.getRequestURL() = http://localhost:8080/aura/view/admin/adminLoad.jsp
										request.getServletPath() = /view/admin/adminLoad.jsp
                                	--%>
                                	<%-- 
                                	<%
                                		/* 
	                                	System.out.println("request.getRequestURI() = " + request.getRequestURI());   
	                            		System.out.println("request.getContextPath() = " + request.getContextPath());   
	                            		System.out.println("request.getRequestURL() = " + request.getRequestURL());   
	                            		System.out.println("request.getServletPath() = " + request.getServletPath());   
                            			 */
	                                	String tUrl = (request.getRequestURL()).toString();
                            			/* 
	                                	System.out.println("indexOf = " + tUrl.indexOf("/aura") );
	                                	System.out.println("substring = " + tUrl.substring(tUrl.indexOf("/aura")));
	                                	 */
                                	%>
                                	 --%>
                                	<%-- 이전:${ pages }" / 현재 map --%>
                                	<%-- 
                                	<a href="${pageContext.request.contextPath}/${commAt['category']}/${cmd}">${commAt["pagesName"]}</a> 
                                	 --%>
                                    <li>${commAt["pagesName"]}<span class="bread-slash"> / </span>
                                    <%--
                                    <li><a href="${pageContext.request.contextPath}/${cmd}">${ pages }</a> <span class="bread-slash">/</span>
                                     --%>
                                    
                                    </li>
                                    <li><span class="bread-blod">${commAt["categoryName"]}</span>
                                    </li>
                                </ul>
                            </div>
                        	<!-- 
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <div class="breadcome-heading">
                                    <form role="search" class="sr-input-func">
                                        <input type="text" placeholder="Search..." class="search-int form-control">
                                        <a href="#"><i class="fa fa-search"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <ul class="breadcome-menu">
                                    <li><a href="#">카테고리명</a> <span class="bread-slash">/</span>
                                    </li>
                                    <li><span class="bread-blod">페이지명</span>
                                    </li>
                                </ul>
                            </div>
                             -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</div>