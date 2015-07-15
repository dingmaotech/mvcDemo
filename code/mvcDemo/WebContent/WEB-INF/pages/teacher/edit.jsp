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
<meta name="description" content="修改系统教师信息">
<meta name="keywords" content="教师，修改教师">
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="<c:url value='/frame/images/favicon/mstile-144x144.png'/>">
<!-- For Windows Phone -->
<title>教学管理系统-修改教师</title>
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
	    if($.trim($("#teachername").val())==''){
	          alert("请输入教师名称！");
	          return ;
	     }
	     $('#editForm').submit(); 
	  }
      
	  //表单重置
	  function resetForm(){
		 $('#editForm')[0].reset(); 
	 }
 </script>
</head>
	<!--当前位置 start-->
	<div id="breadcrumbs-wrapper" class="  lighten-3">
		<div class="container">
			<div class="row">
				<div class="col s12 m12 l12">
					<ol class="breadcrumb">
						<li><a href="index.html">首页</a></li>
						<li><a href="<c:url value='/teacher/enterList.do'/>" >教师管理</a></li>
						<li class="active">修改教师</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!--当前位置 end-->
	<!--当前位置 start-->
	<div id="breadcrumbs-wrapper" class="  lighten-3">
		<div class="container">
			<div class="row">
				<div class="col s12 m12 l12">
					<ol class="breadcrumb">
						<li><a href="index.html">首页</a></li>
						<li><a href="<c:url value='/user/enterList.do'/>" >用户管理</a></li>
						<li class="active">修改用户</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!--当前位置 end-->
	<div class="divider"></div>
	<div id="input-fields" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="col s12" id="editForm" name="editForm" method="post"
				action="<c:url value='/teacher/update.do'/>">
				<input type="hidden" name="id" value="${teacher.id}">
				<div class="row">
					<div class="input-field col s12 center">
						<h4>修改教师</h4>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-person-outline prefix active"></i> <label
							for="teachername" class="center-align active">教师名：</label> <input
							type="text"  id="teachername"
							name="name" value="${teacher.name}" class="validate">
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<a class="btn waves-effect waves-light col s5"
							href="javascript:submitForm()">修改</a> 
						<a class="btn waves-effect waves-light col s5"
							href="javascript:history.go(-1)">返回</a>
					</div>
					<div class="input-field col s12"></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>