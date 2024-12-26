<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>

<script type="text/javascript">
	$( ()=> {
		
		$("#deptAdd").on("click", ()=> {
			loadDept();
		});
		
		$("#posAdd").on("click", ()=> {
			loadPos();
		});
	});
	
	function loadDept(){
		$.ajax({
	        url:"adminasync", // AAdminController.java로 접근
	        type: "post",
	        data : {cmd : "selectDept"}, 
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
	        success: (data) => {
	        	let rows = data;
				$("tr[name='deptList']").empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='deptList'>";
					appendText += "<td class='text-center'><a onclick='addDept("+row.deptNo+", \""+row.posName+"\")'>"+row.deptNo+"</td>'";
					appendText += "<td class='text-center'><a onclick='addDept("+row.deptNo+", \""+row.deptName+"\")'>"+row.deptName+"</td>'";
					appendText +="</tr>";
					
					$("#selectDept").append(appendText);
				});
	        },
	        error:function(request, err) {
	        	console.log("error");
	        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        },
	        complete: function () {
	        }
	    });
	}
	
	function loadPos(){
		$.ajax({
	        url:"adminasync", 
	        type: "post",
	        data : {cmd : "selectPos"}, 
			dataType: 'json',  //json파일 형식으로 값 받기 (JSON.parse(data))
	        success: (data) => {
	        	let rows = data;
				$("tr[name='posList']").empty();
				$.each(rows, (idx, row) => {
					// console.log(row);
					let appendText = "";
					appendText = "<tr name='posList'>";
					appendText += "<td class='text-center'><a onclick='addPos("+row.posNo+", \""+row.posName+"\")'>"+row.posNo+"</td>'";
					appendText += "<td class='text-center'><a onclick='addPos("+row.posNo+", \""+row.posName+"\")'>"+row.posName+"</td>'";
					appendText +="</tr>";
					
					$("#selectPos").append(appendText);
				});
	        },
	        error:function(request, err) {
	        	console.log("error");
	        	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        },
	        complete: function () {
	        }
	    });
	}
	
	function addPos(no, name) {
		console.log("no = " + no);
		console.log("name = " + name);
		$("#posNo").val(no);
		$("#posName").val(name);
		$("#posModal").modal('hide')
	}

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
	                            <div class="col-lg-2 col-lg-2 col-sm-2 col-xs-2">
	                            </div>
	                            <%-- 찐찐 개별 --%>
	                            <div class="col-lg-8 col-lg-8 col-sm-12 col-xs-12">
									<!-- 사원 이름 , 부서, 직급, 입사일자 -->
		                            <form action="admin">
		                                <div class="form-group-inner">
		                                    <div class="row">
		                                        <div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">
		                                            <label class="login2 pull-left pull-left-pro">사원명</label>
		                                        </div>
		                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		                                            <input type="text" class="form-control">
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="form-group-inner">
		                                    <div class="row">
		                                        <div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">
		                                            <label class="login2 pull-left pull-left-pro">부서</label>
		                                        </div>
		                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		                                        	<input type="hidden" name="deptNo">
		                                            <input type="text" name="deptName" class="form-control" 
		                                            		disabled="disabled" placeholder="부서명" style="width: 80%; float: left;">
		                                            		
		                                            <button type="button" id="deptAdd" class="btn btn-custon-four btn-success" 
	                                            		data-toggle="modal" data-target=".search-dept-modal" 
	                                            		style="width: 18%; margin-left: 2%; line-height: 26px;">
	                                            		<i class="fa fa-check edu-checked-pro" aria-hidden="true"></i>부서
		                                            </button>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="form-group-inner">
		                                    <div class="row">
		                                        <div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">
		                                            <label class="login2 pull-left pull-left-pro">직급</label>
		                                        </div>
		                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		                                        	<input type="hidden" id="posNo" name="posNo">
		                                            <input type="text" id="posName" name="posName" class="form-control" 
		                                            		disabled="disabled" placeholder="직급명" style="width: 80%; float: left;">
		                                            		
		                                            <button type="button" id="posAdd" class="btn btn-custon-four btn-success" 
	                                            		data-toggle="modal" data-target=".search-pos-modal" 
	                                            		style="width: 18%; margin-left: 2%; line-height: 26px;">
	                                            		<i class="fa fa-check edu-checked-pro" aria-hidden="true"></i>직급
		                                            </button>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="form-group-inner">
		                                    <div class="row">
		                                        <div class="col-lg-4 col-md-3 col-sm-3 col-xs-12">
		                                            <label class="login2 pull-left pull-left-pro">입사일자(미선택시 현재날짜로 등록)</label>
		                                        </div>
		                                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
		                                            <input type="date" name="hiredate" class="form-control" required pattern="\d{4}-\d{2}-\d{2}" />
	                                        	</div>
		                                    </div>
		                                </div>
		                                <!-- 
		                                <div class="form-group-inner">
		                                    <div class="row">
		                                        <div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
		                                            <label class="login2 pull-left pull-left-pro">Email</label>
		                                        </div>
		                                        <div class="col-lg-6 col-lg-6 col-lg-6 col-xs-12">
		                                            <input type="text" class="form-control" placeholder="gmail 또는 naver메일 입력하세요">
		                                        </div>
		                                        <div class="col-sm-3 col-md-3 col-sm-3 col-xs-12">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">@</span>
		                                                <select class="form-control custom-select-value" name="account">
															<option value="@gmail.com">gmail.com</option>
															<option value="@naver.com">naver.com</option>
														</select>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                                 -->
		                                
		                                <div class="form-group-inner mg-tp-10">
		                                    <div class="login-btn-inner">
		                                        <div class="row">
		                                            <div class="col-lg-12 text-center">
		                                                <div class="login-horizental cancel-wp pull-center form-bc-ele">
		                                                    <button class="btn pd-setting" type="submit">사원 등록</button>
		                                                    <button class="btn btn-default" type="reset">Cancel</button>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </form>
                           </div> 
                           
                           
                        </div>
                    </div>
				</div>
			</div>
		</div>
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>
	
	<!-- 부서 모달 -->
	<div class="modal fade search-dept-modal" id="deptModal" tabindex="-1" role="dialog" aria-labelledby="deptModalLabel" > <!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="deptModalLabel">부서 조회</h4>
	    		</div>
	    		<div class="modal-body">
	    			
	    			<div class="asset-inner">
						<table id="selectDept">
							<tr>
								<th class="text-center col-sm-2">부서번호</th>
								<th class="text-center col-sm-4">부서명</th>
							</tr>
						</table>
					</div>
	    			
	    		</div>
			</div>
		</div>
	</div>
	<!-- 직급 모달 -->
	<div class="modal fade search-pos-modal" id="posModal" tabindex="-1" role="dialog" aria-labelledby="posModalLabel" > <!-- aria-hidden="true" -->
		<div class="modal-dialog modal-lg middleMoalWd">
	    	<div class="modal-content">
	    		<div class="modal-header">
	    			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="posModalLabel">직급 조회</h4>
	    		</div>
	    		<div class="modal-body">
	    			<div class="product-status-wrap">
		    			<div class="asset-inner">
							<table id="selectPos">
								<tr>
									<th class="text-center col-sm-2">직급번호</th>
									<th class="text-center col-sm-4">직급명</th>
								</tr>
							</table>
						</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>