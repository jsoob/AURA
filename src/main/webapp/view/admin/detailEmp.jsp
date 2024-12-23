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
		
		$("#modifyEmp").on("click", ()=> {
			modifyEmp();
		});
		
		function modifyEmp() {
			let form = document.createElement('form');
		       
		    let obj = document.createElement('input');
		   	obj.setAttribute('type', 'hidden');
		  	obj.setAttribute('name', 'empNo');
		  	obj.setAttribute('value', ${empVo.empNo});
		  	form.appendChild(obj);
		  	
		  	form.setAttribute('method', 'post');
		  	form.setAttribute('action', 'admin?cmd=modifyEmp');
		    document.body.appendChild(form);
		    form.submit();
		}
	});
	
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
                        <div class="product-status-wrap aura_content" style="padding-top: 4%;">
		                     <form action="admin">
	                            <div class="col-lg-12 col-sm-12 col-xs-12">
		                            
		                            <div class="col-lg-2 col-sm-2 col-xs-2">
		                            </div>
		                            <%-- 찐찐 개별 --%>
		                            <div class="col-lg-5 col-sm-5 col-xs-5">
										<!-- 사원 이름 , 부서, 직급, 입사일자 -->
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">사원번호</label>
			                                        </div>
			                                        <div class="col-lg-4 col-md-8 col-sm-8 col-xs-12">
			                                            <input type="text" name="empNo" value="${empVo.empNo}" class="form-control" disabled="disabled">
			                                        </div>
			                                        
			                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">생일</label>
			                                        </div>
			                                        <div class="col-lg-4 col-md-8 col-sm-8 col-xs-12">
			                                            <input type="text" name="birthdate" value="${empVo.birthdate}" class="form-control" disabled="disabled" />
		                                        	</div>
			                                    </div>
			                                </div>
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">사원명</label>
			                                        </div>
			                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
			                                            <input type="text" name="empName" value="${empVo.empName}" class="form-control" disabled="disabled">
			                                        </div>
			                                    </div>
			                                </div>
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">부서</label>
			                                        </div>
			                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
			                                        	<input type="hidden" name="deptNo"  value="${empVo.deptNo}">
			                                            <input type="text" name="deptName" value="${empVo.deptName}" class="form-control" disabled="disabled">
			                                        </div>
			                                    </div>
			                                </div>
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">직급</label>
			                                        </div>
			                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
			                                        	<input type="hidden" name="posNo" value="${empVo.posNo}">
			                                            <input type="text" name="posName" class="form-control" value="${empVo.posName}" disabled="disabled">
			                                        </div>
			                                        
			                                    </div>
			                                </div>
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">외부 이메일</label>
			                                        </div>
			                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
			                                        	<input type="text" name="deptName" value="${empVo.empEmail}" class="form-control" disabled="disabled">
			                                        </div>
			                                    </div>
			                                </div>
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-3 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">휴대폰</label>
			                                        </div>
			                                        <div class="col-lg-10 col-md-8 col-sm-8 col-xs-12">
			                                            <input type="text" name="posName" value="${empVo.cellphone}" class="form-control" disabled="disabled" />
			                                        </div>
			                                        
			                                    </div>
			                                </div>
			                                
			                                
			                                <div class="form-group-inner mg-bt-20">
			                                    <div class="row">
			                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">입사일자</label>
			                                        </div>
			                                        <div class="col-lg-4 col-md-4 col-sm-8 col-xs-12">
			                                            <input type="text" name="hiredate" value="${empVo.hiredate}" class="form-control" disabled="disabled" />
		                                        	</div>
			                                        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-12">
			                                            <label class="login2 pull-left pull-left-pro">퇴사일자</label>
			                                        </div>
			                                        <div class="col-lg-4 col-md-4 col-sm-8 col-xs-12">
			                                            <input type="text" name="quitdate" value="${empVo.quitdate}" class="form-control" disabled="disabled" />
		                                        	</div>
			                                    </div>
			                                </div>
			                                
	                           		</div> 
	                           
	                           		<div class="col-lg-3 col-sm-3 col-xs-3" style="height: 420px;">
	                           			<div class="col-lg-12 text-center" style="line-height: 400px;">
                                        	<img class="mg-ht-10 viewImg" alt="사원이미지 없음" src="/aura/img/product/pro4.jpg"></a><!-- ${vo.empImage} -->
                                        </div>
	                           		</div>
	                           		
	                           		<div class="col-lg-12 col-sm-12 col-xs-12">
	                          			<div class="form-group-inner mg-tp-10">
	                          				<div class="login-btn-inner">
		                                        <div class="row">
		                                            <div class="col-lg-12 text-center">
		                                                <div class="cancel-wp pull-center form-bc-ele"> <!-- login-horizental -->
		                                                    <button class="btn pd-setting" type="button" id="modifyEmp">정보 수정</button>
		                                                    <a href="admin?cmd=selectEmp" class="btn btn-default">목록</a>
		                                                </div>
		                                            </div>
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
		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>
	
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>

</body>
</html>