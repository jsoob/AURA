<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<%-- header 영역에서 첨부된 css 파일+js --%>
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<!-- 
<script type="text/javascript">
	$( ()=> {
		fnSetGrid();
	});
	
	function fnSetGrid(){
		console.log('fnSetGrid()');
		
		let rowArr = [];
		
		$('#gridList').w2grid({ 
	        name: 'gridList',
	        show: {
	        	selectColumn: true,
				lineNumbers : true,
	            footer: true
	        },
	        columns: [                
				{ field:'deptNo', caption:'부서번호', size:'10%', style:'text-align:center'},
				{ field:'deptName', caption:'부서명', size:'10%', style:'text-align:center'},
				{ field:'empNo', caption:'사원번호', hidden:true},
				{ field:'empName', caption:'사원명', size:'10%', style:'text-align:center'},
				{ field:'col5', caption:'생산여부', size:'10%'},
				{ field:'col6', caption:'고객사 코드', hidden:true},
				{ field:'col7', caption:'고객사명', size:'15%'},
				{ field:'col8', caption:'설명', size:'15%'}
				], 
			records: rowArr,
			onReload: function(event) {
				fnLoadGrid();
			},
			onClick: function (event) {}
		});
		fnLoadGrid();
	}
	
	function fnLoadGrid() {
		
	}
</script>
 -->
</head>
<body>
	<%-- Start Left menu area --%>
    <jsp:include page="/view/comm/sidebar.jsp"></jsp:include>
    
    <%-- End Left menu area --%>
    <%-- Start Welcome area --%>
	<div class="all-content-wrapper">
        <jsp:include page="/view/comm/header.jsp"></jsp:include>
        
        <div class="container-area mg-b-15">
            <div class="container-fluid">
                <!-- 여기부터 개별 -->
                <div class="row">
                	
                	<div class="" style="margin-bottom: 20px;">
                        <div class="row">
                        	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        		<div id="gridList" style="width: 100%; height: 620px;"></div>
                            </div>
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