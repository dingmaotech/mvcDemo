<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description" content="修改系统学生信息">
<meta name="keywords" content="学生，修改学生">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="<c:url value='/frame/images/favicon/mstile-144x144.png'/>">
<!-- For Windows Phone -->
<title>教学管理系统-修改学生</title>
<!-- Favicons-->
<link rel="icon"
	href="<c:url value='/frame/images/favicon/favicon-32x32.png'/>"
	sizes="32x32">
<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="<c:url value='/frame/images/favicon/apple-touch-icon-152x152.png'/>">
<!-- CORE CSS-->
<link href="<c:url value='/frame/css/materialize.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/frame/css/style.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/frame/css/page-center.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">

<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!--materialize js-->
<script type="text/javascript"
	src="<c:url value='/frame/js/materialize.js'/>"></script>
<!--prism-->
<script type="text/javascript" src="<c:url value='/frame/js/prism.js'/>"></script>
<!--scrollbar-->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'/>"></script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins.js'/>"></script>
</head>
<body class="cyan">
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Page Loading -->
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<div class="row">
				<div class="input-field col s12 center">
					<h4>学生详情</h4>
				</div>
			</div>
			<div class="row margin">
				<div class="input-field col s6">
					<i class="mdi-av-my-library-books prefix"></i> <label for="name"
						class="center-align">学号：</label> <input type="text" id="stuNo"
						name="stuNo" value="${student.stuNo}">
				</div>
				<div class="input-field col s6">
					<i class="mdi-av-my-library-books prefix"></i> <label for="name"
						class="center-align">姓名：</label> <input type="text" id="stuName"
						name="stuName" value="${student.stuName }">
				</div>
			</div>
			<div class="row margin">
				<div class="input-field col s6">
					<i class="mdi-av-my-library-books prefix"></i> <input name="stuSex"
						type="radio" id="boy" value="0" /> <label for="boy">男</label> <input
						name="stuSex" type="radio" id="girl" value="1" /> <label
						for="girl">女</label>
				</div>
				<div class="input-field col s6">
					<i class="mdi-av-my-library-books prefix"></i> <label
						for="birthday" class="center-align">出生日期：</label> <input
						type="date" id="birthday" name="birthday"
						value="${student.birthday }" class="datepicker place">
				</div>
			</div>
			<div class="row margin">
				<div class="input-field col s6">
					<i class="mdi-communication-call prefix"></i> <label for="stuTel"
						class="center-align">学生电话：</label> <input type="text" id="stuTel"
						name="stuTel" value="${student.stuTel}">
				</div>
				<div class="input-field col s6">
					<i class="mdi-communication-call prefix"></i> <label for="homeTel"
						class="center-align">家庭电话：</label> <input type="text" id="homeTel"
						name="homeTel" value="${student.homeTel }">
				</div>
			</div>
			<div class="row margin">
				<div class="input-field col s12">
					<i class="mdi-social-domain prefix"></i> <label for="dormitoryAddr"
						class="center-align">宿舍地址：</label> <input type="text"
						id="dormitoryAddr" name="dormitoryAddr"
						value="${student.dormitoryAddr}">
				</div>
			</div>
			<div class="row margin">
				<div class="input-field col s12">
					<i class="mdi-social-domain prefix"></i> <label for="stuHomeAddr"
						class="center-align">家庭住址：</label> <input type="text"
						id="homeAddr" name="homeAddr" value="${student.homeAddr}">
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<a class="btn waves-effect waves-light col s5"
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="input-field col s12"></div>
			</div>
		</div>
	</div>
</body>
</html>