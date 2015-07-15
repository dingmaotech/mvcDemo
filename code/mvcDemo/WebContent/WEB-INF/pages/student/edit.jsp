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
<link rel="apple-touch-icon-precomposed"	href="<c:url value='/frame/images/favicon/apple-touch-icon-152x152.png'/>">
<!-- CORE CSS-->
<link href="<c:url value='/frame/css/materialize.css'/>" type="text/css"	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/frame/css/style.css'/>" type="text/css"	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/frame/css/page-center.css'/>" type="text/css"	rel="stylesheet" media="screen,projection">

<!-- jQuery Library -->
<script type="text/javascript"	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!--materialize js-->
<script type="text/javascript"	src="<c:url value='/frame/js/materialize.js'/>"></script>
<!--prism-->
<script type="text/javascript" src="<c:url value='/frame/js/prism.js'/>"></script>
<!--scrollbar-->
<script type="text/javascript"	src="<c:url value='/frame/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'/>"></script>
<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"	src="<c:url value='/frame/js/plugins.js'/>"></script>
	
<script type="text/javascript">
	$(document).ready(function(){
		/* var sex = "${student.stuSex}";
		 $('input[name="stuSex"]').each(function(){ 
			 alert(sex);
		    if(this.value == sex ){
		    	this.attr("checked",true);  
		    }
		}); */  
	});

      //表单提交
	  function submitForm(){
	    if($.trim($("#stuName").val())==''){
	          alert("请输入学生姓名！");
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
<body >
	<!--当前位置 start-->
	<div id="breadcrumbs-wrapper" class="  lighten-3">
		<div class="container">
			<div class="row">
				<div class="col s12 m12 l12">
					<ol class="breadcrumb">
						<li><a href="index.html">首页</a></li>
						<li><a href="<c:url value='/student/enterList.do'/>" >学生管理</a></li>
						<li class="active">修改学生</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
	<!--当前位置 end-->
	<div class="divider"></div>
	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<form class="add-student-form" id="editForm" name="editForm"
				method="post" action="<c:url value='/student/update.do'/>">
				<input type="hidden" name="stuId" value="${student.stuId }">
				<div class="row">
					<div class="input-field col s12 center">
						<h4>修改学生</h4>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s6">
						<i class="mdi-av-my-library-books prefix  active"></i> <label for="name"
							class="validate center-align  active">学号：</label> <input type="text"
							id="stuNo" name="stuNo" value="${student.stuNo}"
							placeholder="${student.stuNo}">
					</div>
					<div class="input-field col s6">
						<i class="mdi-social-person-outline prefix  active"></i> <label for="name"
							class="center-align  active">姓名：</label> <input type="text" id="stuName"
							name="stuName" value="${student.stuName }"
							placeholder="${student.stuName}">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s6">
						<i class="mdi-av-my-library-books prefix  active"></i> 
						<input name="stuSex" type="radio" id="boy" <c:if test='${student.stuSex eq 0}'>checked=checked </c:if>  value="0" /> 
						<label for="boy">男</label>
						<input name="stuSex" type="radio" id="girl"  <c:if test='${student.stuSex eq 1}'>checked=checked </c:if> value="1" /> 
						<label for="girl">女</label>
					</div>
					<div class="input-field col s6">
						<i class="mdi-av-my-library-books prefix  active"></i> <label
							for="birthday" class="center-align  active">出生日期：</label> <input
							type="date" id="birthday" name="birthday"
							value="${student.birthday }" class="datepicker"
							placeholder="${student.birthday}">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s6">
						<i class="mdi-communication-call prefix  active"></i> <label for="stuTel"
							class="center-align  active" >学生电话：</label> <input type="text" id="stuTel"
							name="stuTel" value="${student.stuTel}"
							placeholder="${student.stuTel}">
					</div>
					<div class="input-field col s6">
						<i class="mdi-communication-call prefix  active"></i> <label for="homeTel"
							class="center-align  active">家庭电话：</label> <input type="text"
							id="homeTel" name="homeTel" value="${student.homeTel }"
							placeholder="${student.homeTel }">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-domain prefix  active"></i> <label
							for="dormitoryAddr" class="center-align  active">宿舍地址：</label> <input
							type="text" id="dormitoryAddr" name="dormitoryAddr"
							value="${student.dormitoryAddr}"
							placeholder="${student.dormitoryAddr}">
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="mdi-social-domain prefix  active"></i> <label for="stuHomeAddr"
							class="center-align  active">家庭住址：</label> <input type="text"
							id="homeAddr" name="homeAddr" value="${student.homeAddr}"
							placeholder="${student.homeAddr}">
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