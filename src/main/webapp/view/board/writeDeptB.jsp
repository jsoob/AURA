<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${commAt["title"]}</title>
<!-- 공통 CSS 및 JS 파일 -->
<jsp:include page="/view/comm/headCss.jsp"></jsp:include>
<!-- jQuery 라이브러리 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Summernote CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.css" rel="stylesheet">
</head>
<body>
	<!-- 좌측 메뉴 포함 -->
	<div class="left-sidebar">
		<jsp:include page="/view/comm/sidebar.jsp"></jsp:include>
	</div>

	<!-- 페이지 메인 컨텐츠 시작 -->
	<div class="all-content-wrapper">
		<!-- 상단 헤더 포함 -->
		<jsp:include page="/view/comm/header.jsp"></jsp:include>

		<div class="container-area mg-b-15">
			<div class="container-fluid">
				<div class="row">
					<!-- 글쓰기 폼 -->
					<form action="freeboard" method="post">
						<table class="table">
							<!-- 제목 입력 -->
							<tr>
								<td colspan="2">
									<div class="row">
										<div class="col-sm-1">
											<label for="freeBTitle" class="control-label">제목:</label>
										</div>
										<div class="col-sm-11">
											<input type="text" name="freeBTitle" class="form-control" id="freeBTitle" />
										</div>
									</div>
								</td>
							</tr>

							<!-- Summernote 에디터로 내용 입력 -->
							<tr>
								<td colspan="2">
									<textarea class="summernote6" name="freeBContent" id="summernote6"></textarea>
								</td>
							</tr>

							<!-- 공지 설정 -->
							<tr>
								<td colspan="2">
									공지로 등록: <input type="checkbox" name="freeBNotice" value="1">
								</td>
							</tr>

							<!-- 공개/비공개 설정 -->
							<tr>
								<td colspan="2">
									<input type="radio" name="freeBPblc" value="1"> 공개
									<input type="radio" name="freeBPblc" value="0"> 비공개
								</td>
							</tr>

							<!-- 버튼 영역 -->
							<tr>
								<td colspan="2">
									<input type="button" class="btn btn-outline-primary" value="목록" onclick="location.href='freeboard?cmd=listFreeB';" />
									<input type="button" class="btn btn-outline-success" name="freeBStatus" value="임시저장" />
									<input type="submit" class="btn btn-outline-success" value="등록" />
									<input type="button" class="btn btn-outline-danger" id="resetBtn" value="다시쓰기" />
								</td>
							</tr>
						</table>

						<!-- 숨겨진 필드로 명령 전달 -->
						<input type="hidden" name="cmd" value="writeFreeB" />
					</form>
				</div>
			</div>
		</div>

		<!-- 하단 푸터 포함 -->
		<div class="footer">
			<jsp:include page="/view/comm/footer.jsp"></jsp:include>
		</div>
	</div>

	<!-- 공통 JS 파일 -->
	<jsp:include page="/view/comm/footerJs.jsp"></jsp:include>
	<!-- Summernote JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.js"></script>

	<script>
		$(document).ready(function() {
			// Summernote 초기화
			$('.summernote6').summernote({
				height: 300, // 에디터 높이
				placeholder: '여기에 내용을 입력하세요...',
				focus: true // 로드 시 포커스
			});

			// 다시쓰기 버튼 동작
			$('#resetBtn').on('click', function() {
				// Summernote 에디터 내용 초기화
				$('.summernote6').summernote('reset');
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