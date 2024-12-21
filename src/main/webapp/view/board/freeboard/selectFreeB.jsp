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

							<!-- 검색부분 -->
							<div class="mg-bt-10">
								<div class="form-inline">
									<div class="pull-left">
									
										<label>전체글(${totalCount})</label>
									
										<a href="freeboard?cmd=writeFreeBForm"> <input
											type="button" class="btn btn-outline-primary" value="글쓰기" />
										</a>
									</div>
									<div class="pull-right">
										
										<div class="form-group">
											
											
											<select name="orderBy" class="form-control" id="orderBy">
								                <option value="new">최신순</option>
								                <option value="old">오래된순</option>
								                <option value="view">조회순</option>
								            </select>
											
											<select name="search" class="form-control" id="search">
								                <option value="title">제목</option>
								                <option value="content">내용</option>
								                <option value="writer">작성자</option>
								            </select>
											
											<input type="text" class="form-control mg-wd-10"
												id="searchWord" placeholder="검색어를 입력하세요">
										</div>

										<span class="pd-lt-10">
											<button type="button" class="btn pd-setting">검색</button>
										</span>
									</div>
								</div>
							</div>
							<table class="table table-striped">
								<tr>
									<th>게시판번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>등록일자</th>
									<th>조회수</th>
								</tr>
								<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.freeBNo}</td>
										<td><a
											href="freeboard?cmd=detailFreeB&freeBNo=${vo.freeBNo}">
												${vo.freeBTitle}</a></td>
										<td>${vo.freeBCrtr}</td>
										<td>${vo.createDate}</td>
										<td>${vo.freeBView}</td>
									</tr>
								</c:forEach>
							</table>
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