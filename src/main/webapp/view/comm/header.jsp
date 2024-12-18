<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="logo-pro">

				<a href="index.html"><img class="main-logo" src="${pageContext.request.contextPath}/img/logo/logo.png" alt="" /></a>
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
                            <div class="col-lg-1 col-md-0 col-sm-1 col-xs-12">
                                <div class="menu-switcher-pro">
                                    <button type="button" id="sidebarCollapse" class="btn bar-button-pro header-drl-controller-btn btn-info navbar-btn">
										<i class="fa fa-list"></i>
										<!-- "educate-icon educate-nav"  -->
									</button>
                                </div>
                            </div>
                            
                            <div class="col-lg-6 col-md-7 col-sm-6 col-xs-12">
                                <div class="header-top-menu tabl-d-n">
                                   <%-- 관리자에서만 보여주는 메뉴 --%>
                                    <c:if test="${loginEmp.empNo eq '2024000' }">
	                                    <ul class="nav navbar-nav mai-top-nav">
	                                        <li class="nav-item"><a href="${pageContext.request.contextPath}/admin?cmd=adminLoad" class="nav-link">관리자 조회</a>
	                                        </li>
	                                        
	                                        <li class="nav-item"><a href="#" class="nav-link">부서관리</a>
	                                        </li>
	                                        <li class="nav-item"><a href="#" class="nav-link">직급관리</a>
	                                        </li>
	                                        <li class="nav-item"><a href="#" class="nav-link">사원관리</a>
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
                                            <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle"><i class="educate-icon educate-message edu-chat-pro" aria-hidden="true"></i><span class="indicator-ms"></span></a>
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
                                        <li class="nav-item"><a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle"><i class="educate-icon educate-bell" aria-hidden="true"></i><span class="indicator-nt"></span></a>
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
												<img src="${pageContext.request.contextPath}/img/product/pro4.jpg" alt="" />
												<span class="admin-name">AURA 사용자명</span>
												<i class="fa fa-angle-down edu-icon edu-down-arrow"></i>
											</a>
                                            <ul role="menu" class="dropdown-header-top author-log dropdown-menu animated zoomIn">
                                                <li><a href="#"><span class="edu-icon edu-home-admin author-log-ic"></span>My Account</a>
                                                </li>
                                                <li><a href="${pageContext.request.contextPath}/mypage?cmd=detail" ><span class="edu-icon edu-user-rounded author-log-ic"></span>My Page</a>
                                                </li>
                                                <li><a href="#"><span class="edu-icon edu-money author-log-ic"></span>User Billing</a>
                                                </li>
                                                <li><a href="#"><span class="edu-icon edu-settings author-log-ic"></span>Settings</a>
                                                </li>
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
                                	
                                	<%
	                                	System.out.println("request.getRequestURI() = " + request.getRequestURI());   
	                            		System.out.println("request.getContextPath() = " + request.getContextPath());   
	                            		System.out.println("request.getRequestURL() = " + request.getRequestURL());   
	                            		System.out.println("request.getServletPath() = " + request.getServletPath());   
                            			
	                                	String tUrl = (request.getRequestURL()).toString();
	                                	System.out.println("indexOf = " + tUrl.indexOf("/aura") );
	                                	System.out.println("substring = " + tUrl.substring(tUrl.indexOf("/aura")));
                                	%>
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