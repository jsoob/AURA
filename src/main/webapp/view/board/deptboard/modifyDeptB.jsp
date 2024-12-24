<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- header 영역에서 첨부된 css 파일+js -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<!-- Summernote CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.css" rel="stylesheet">
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
					<form action="deptboard">
						<table class="table">
							<tr>
								<td colspan="2">
									<div class="col-sm-1">
										<label class="control-label text-left">제목:</label>
									</div>
									<div class="col-sm-11">
										<input type="text" name="deptBTitle" class="form-control"
											id="" value="${vo.deptBTitle}" required />
									</div> 
									
									
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<textarea class="summernote6" id="summernote" name="deptBContent" cols="50" rows="10">
										${vo.deptBContent}
									</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">공지로 등록 
									<input type="checkbox" name="deptBNotice" value="1">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="radio" name="deptBPblc" value="1" checked="checked" required> 공개 
									<input type="radio" name="deptBPblc" value="0"> 비공개
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a href="deptboard?cmd=selectDeptB">
										<input type="button" class="btn btn-outline-primary" value="취소" />
									</a> 
									<input type="submit" class="btn btn-outline-success" value="등록" /> 
									<input type="button" class="btn btn-outline-danger" id="btn" value="다시쓰기" />
								</td>
							</tr>
						</table>
						<input type="hidden" name="cmd" value="modifyDeptBOk" />
						<input type="hidden" name="deptBNo" value="${vo.deptBNo}" />
					</form>
				</div>
			</div>
		</div>

		<jsp:include page="/view/comm/footer.jsp"></jsp:include>
	</div>

	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
	<!-- Summernote JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.js"></script>

	<script>
    $(document).ready(function() {
        // Summernote 초기화
        $('#summernote').summernote({
            height: 300, // 에디터 높이
            placeholder: '여기에 내용을 입력하세요...',
            focus: true // 로드 시 포커스
        });

        // 다시쓰기 버튼 동작
        $('#btn').on('click', function() {
            // Summernote 에디터 내용 초기화
            $('#summernote').summernote('reset');
            // 텍스트 필드 초기화
            $('input[type="text"]').val('');
            // 체크박스 및 라디오 버튼 초기화
            $('input[type="checkbox"]').prop('checked', false);
            $('input[type="radio"]').prop('checked', false);
        });
    });
</script>
</body>
</html>