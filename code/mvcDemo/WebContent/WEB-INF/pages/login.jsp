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
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="msapplication-tap-highlight" content="no">
  <meta name="description" content="教务管理系统-用户登录 ">
  <meta name="keywords" content="教务管理系统, 用户登录">
  <title>教务管理系统——用户登录</title>

  <!-- Favicons-->
  <link rel="icon" href="<c:url value='/frame/images/favicon/favicon-32x32.png'/>" sizes="32x32">
  <!-- Favicons-->
  <link rel="apple-touch-icon-precomposed" href="<c:url value='/frame/images/favicon/apple-touch-icon-152x152.png'/>">
  <!-- For iPhone -->
  <meta name="msapplication-TileColor" content="#00bcd4">
  <meta name="msapplication-TileImage" content="<c:url value='/frame/images/favicon/mstile-144x144.png'/>">
  <!-- For Windows Phone -->


  <!-- CORE CSS-->
  
  <link href="<c:url value='/frame/css/materialize.css'/>" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<c:url value='/frame/css/style.css'/>" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<c:url value='/frame/css/page-center.css'/>" type="text/css" rel="stylesheet" media="screen,projection">

  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="<c:url value='/frame/css/prism.css'/>" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="<c:url value='/frame/js/plugins/perfect-scrollbar/perfect-scrollbar.css'/>" type="text/css" rel="stylesheet" media="screen,projection">
  
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
      <form id="loginForm" class="login-form"  method="post" action="<c:url value='/loginAction/login.do'/>">
        <div class="row">
          <div class="input-field col s12 center">
            <img src="<c:url value='/frame/images/login-logo.png'/>" alt="" class="circle responsive-img valign profile-image-login">
            <p class="center login-form-text">用户登录</p>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix active"></i>
			<label for="username" class="center-align active">用户名：</label> 
			<input id="username" name="username" type="text">
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-action-lock-outline prefix active"></i>
			<label for="password" class="center-align active">密&nbsp;&nbsp;&nbsp;码：</label>
			<input id="password" name="password" type="password">
          </div>
        </div>
        <div class="row">          
          <div class="input-field col s12 m12 l12  login-text">
              <input type="checkbox" id="remember-me" />
              <label for="remember-me">记住密码</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
     		<a class="btn waves-effect waves-light col s5" href="javascript:submitForm()">登录</a>
			<a class="btn waves-effect waves-light col s5" href="javascript:resetForm()">重置</a>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s6 m6 l6">
            <p class="margin medium-small"><a href="page-register.html">注册</a></p>
          </div>
          <div class="input-field col s6 m6 l6">
              <p class="margin right-align medium-small"><a href="page-forgot-password.html">忘记密码 ?</a></p>
          </div>          
        </div>
      </form>
    </div>
  </div>

  <!-- jQuery Library -->
  <script type="text/javascript" src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
  <!--materialize js-->
  <script type="text/javascript" src="<c:url value='/frame/js/materialize.js'/>"></script>
  <!--prism-->
  <script type="text/javascript" src="<c:url value='/frame/js/prism.js'/>"></script>
  <!--scrollbar-->
  <script type="text/javascript" src="<c:url value='/frame/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js'/>"></script>

  <!--plugins.js - Some Specific JS codes for Plugin Settings-->
  <script type="text/javascript" src="<c:url value='/frame/js/plugins.js'/>"></script>
<script type="text/javascript">

		$(document).keydown(function(e){
			if(e.keyCode == 13) {
				submitForm();
			}
		});

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
	      $('#loginForm').submit(); 
	  }
      
	  //表单重置
	  function resetForm(){
		 $('#loginForm')[0].reset(); 
	 }
	  

	  
 </script>
</body>

</html>