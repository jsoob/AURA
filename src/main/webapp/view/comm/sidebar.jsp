<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<!-- Start Left menu area -->
<div class="left-sidebar-pro">
    <nav id="sidebar" class="">
        <div class="sidebar-header">
        	<%-- ../main/main.jsp --%>
            <a href="${pageContext.request.contextPath}/main"><img class="main-logo" src="${pageContext.request.contextPath}/img/logo/AURAlogo.png" alt="" style="width: 200px; height: 60px;" /></a>
            <strong><a href="${pageContext.request.contextPath}/main"><img src="${pageContext.request.contextPath}/img/logo/AURAlogosn.png" alt="" style="width: 45px; height: 38px;" /></a></strong>
        </div>
        <div class="left-custom-menu-adp-wrap comment-scrollbar">
            <nav class="sidebar-nav left-sidebar-menu-pro">
                <ul class="metismenu" id="menu1">
                    <li id="jjj_category">
                        <a class="has-arrow" href="index.html">
						   <!-- <span class="educate-icon educate-home icon-wrap"></span> -->
						   <span class="fa fa-clipboard"></span>
						   <span class="mini-click-non">전자 결재</span>
						</a>
						<!-- 전자결재 카테고리 안의 페이지 -->
                        <ul class="submenu-angle" aria-expanded="true">
                            <li><a title="Dashboard v.1" href="index.html"><span class="mini-sub-pro">전자결재 조회</span></a></li>
                            <li><a title="Dashboard v.2" href="index-1.html"><span class="mini-sub-pro">글쓰기</span></a></li>
                            <li><a title="Dashboard v.3" href="index-2.html"><span class="mini-sub-pro">전자결재 3</span></a></li>
                            <li><a title="Analytics" href="analytics.html"><span class="mini-sub-pro">전자결재 4</span></a></li>
                            <li><a title="Widgets" href="widgets.html"><span class="mini-sub-pro">전자결재 5</span></a></li>
                        </ul>
                    </li>
                    
                    <!-- <li id="board_category"> -->
                   	<c:if test="${ commAt['category'] != null  && commAt['category'] == 'board' }">
                   		<li id="board_category" class="active">
                   	</c:if>
                   	<c:if test="${  category != 'board' }">
                   		<li id="board_category">
                   	</c:if>
                        <a class="has-arrow" href="all-professors.html" aria-expanded="false">
                        	<!-- <span class="educate-icon educate-professor icon-wrap"></span> -->
                        	<span class="fa fa-commenting-o"></span> 
                        	<span class="mini-click-non">게시판</span>
                        </a>
                        <ul class="submenu-angle" aria-expanded="false">
                            <li><a title="All Professors" href="all-professors.html"><span class="mini-sub-pro">전체 게시판</span></a></li>
                            <li><a title="Edit Professor" href="${pageContext.request.contextPath}/deptboard"><span class="mini-sub-pro">부서 게시판</span></a></li>
                            <li><a title="Professor Profile" href="${pageContext.request.contextPath}/freeboard"><span class="mini-sub-pro">자유 게시판</span></a></li>
                        </ul>
                    </li>
                    
                    <!-- <li id="mail_category" class="active"> -->
                    <c:if test="${ commAt['category'] != null  && commAt['category'] == 'mail' }">
                   		<li id="mail_category"  class="active">
                   	</c:if>
                   	<c:if test="${  commAt['category'] != 'mail' }">
                   		<li id="mail_category"  >
                   	</c:if>
                        <a class="has-arrow" href="mailbox.html" aria-expanded="false">
                        	<!-- <span class="educate-icon educate-message icon-wrap"></span> -->
                        	<span class="fa fa-envelope-o"></span>
                        	<span class="mini-click-non">Mailbox</span>
                        </a>
                        <ul class="submenu-angle" aria-expanded="false">
                            <li><a title="Inbox" href="${pageContext.request.contextPath}/mail"><span class="mini-sub-pro">Inbox</span></a></li>
                            <li><a title="View Mail" href="${pageContext.request.contextPath}/mail"><span class="mini-sub-pro">View Mail</span></a></li>
                            <li><a title="Compose Mail" href="${pageContext.request.contextPath}/mail"><span class="mini-sub-pro">Compose Mail</span></a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </nav>
</div>