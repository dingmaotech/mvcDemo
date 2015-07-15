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
<meta name="description" content="添加系统用户信息">
<meta name="keywords" content="用户，添加用户">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="<c:url value='/frame/images/favicon/mstile-144x144.png'/>">
<!-- For Windows Phone -->
<title>教学管理系统-添加用户</title>
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
<script type="text/javascript">
      //表单提交
	  function submitForm(){
 	     if($.trim($("#username").val())==''){
	          alert("请输入用户名信息！");
	          return ;
	     }
	    if($.trim($("#password").val())==''){
	          alert("请输入密码！");
	          return ;
	     }
	    if($.trim($("#password-again").val())==''){
	          alert("请输入确认密码！");
	          return ;
	     }
	    if($.trim($("#password-again").val()) != $.trim($("#password").val())){
	          alert("密码不一致，请重新输入确认密码！");
	          return ;
	     }
	     $('#addForm').submit(); 
	  }
      
	  //表单重置
	  function resetForm(){
		 $('#addForm')[0].reset(); 
	 }
 </script>
</head>
<body>
	<!--当前位置 start-->
	<div id="breadcrumbs-wrapper" class="  lighten-3">
		<div class="container">
			<div class="row">
				<div class="col s12 m12 l12">
					<ol class="breadcrumb">
						<li><a href="index.html">首页</a></li>
						<li><a href="<c:url value='/user/enterList.do'/>">用户管理</a></li>
						<li class="active">添加用户</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!--当前位置 end-->
	<div class="divider"></div>
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="login-form" id="addForm" name="addForm" method="post"
				action="<c:url value='/user/save.do'/>">
				<div class="row">
					<div class="input-field col s12 center">
						<h4>添加用户</h4>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix"></i> <label
							for="username" class="center-align">用户名：</label> <input
							type="text" id="username" name="username" value="">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix"></i> <label
							for="password">密&nbsp;&nbsp;&nbsp;码：</label> <input
							type="password" id="password" value="" name="password">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-action-lock-outline prefix"></i> <input
							type="password" id="password-again" value=""> <label
							for="password-again">确认密码：</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<a class="btn waves-effect waves-light col s5"
							href="javascript:submitForm()">添加</a> <a
							class="btn waves-effect waves-light col s5"
							href="javascript:history.go(-1)">返回</a>
					</div>
					<div class="input-field col s12"></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>