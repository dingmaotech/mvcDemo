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
<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="<c:url value='/frame/images/favicon/mstile-144x144.png'/>">
<!-- For Windows Phone -->
<title>列表页面</title>
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
<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link
	href="<c:url value='/frame/js/plugins/perfect-scrollbar/perfect-scrollbar.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/frame/js/plugins/data-tables/css/jquery.dataTables.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!-- data-tables -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/data-tables/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/data-tables/data-tables-script.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/user/user.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 初始化刪除按钮
	$('#data-table-user tbody').on('click', 'a.delete', function(e) {
		var id = $(this).attr("id");
		e.preventDefault();
		if (confirm("确定要删除该属性？")) {
			usertable.row($(this).parents('tr')).remove().draw();
		}
	});
	
	
	// 初始化修改按钮
	$('#data-table-user tbody').on('click', 'a.edit', function(e) {
		e.preventDefault();
			var table = $('#data-table-user').DataTable();
			window.top.location.href="<c:url value='/loginAction/enterLogin.do'/>";
	});
	
	// 初始化详细按钮
	$('#data-table-user tbody').on('click', 'a.detail', function(e) {
		e.preventDefault();
		window.top.location.href="<c:url value='/loginAction/enterLogin.do'/>";
	});
});
</script>
</head>
<body>
	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">
			<!-- START LEFT SIDEBAR NAV-->
			<aside id="left-sidebar-nav">
			<ul id="slide-out" class="side-nav fixed leftside-navigation">
				<li class="no-padding">
					<ul class="collapsible collapsible-accordion">
						<li class="bold"><a
							class="collapsible-header waves-effect waves-cyan"><i
								class="mdi-device-now-widgets"></i>课程安排</a>
							<div class="collapsible-body">
								<ul>
									<li><a href="#">我的排课</a></li>
									<li><a href="#">基础信息</a></li></li>
					</ul>
		</div>
		</li>
		<li class="bold"><a class="waves-effect waves-cyan"><i
				class="mdi-device-now-widgets"></i> 教案计划 <span class="new badge"></span></a>
		</li>
		<li class="bold"><a class="waves-effect waves-cyan"><i
				class="mdi-device-now-widgets"></i> 我的备课</a></li>
		</ul>
		</li>
		</ul>
		</aside>
		<!-- END LEFT SIDEBAR NAV-->

		<!-- //////////////////////////////////////////////////////////////////////////// -->

		<!-- START CONTENT -->
		<section id="content"> <!--start container-->
		<div class="container">
			<div style="padding: 5px">
				<!--<div class="row" style="margin:10px">
						<div class="col s12 m4 l3 center"><a class="waves-effect waves-light btn cyan">基础信息</a></div>
						<div class="col s12 m4 l3 center"><a class="waves-effect waves-light btn cyan lighten-4 black-text" href="../../page/rule/rule1.html">规则设置</a></div>
						<div class="col s12 m4 l3 center"><a class="waves-effect waves-light btn cyan lighten-4 black-text" href="baseinfo.html">课程微调</a></div>
						<div class="col s12 m4 l3 center"><a class="waves-effect waves-light btn cyan lighten-4 black-text" href="baseinfo.html">课表查看</a></div>
					  </div>-->
				<nav class="red">
				<div class="nav-wrapper">
					<ul class="left hide-on-med-and-down">
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light">基础信息</a></li>
						<li><a href="../../page/rule/rule1.html"
							" class="waves-effect waves-block waves-light">规则设置</a></li>
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light">课程微调</a></li>
						<li><a href="#" class="waves-effect waves-block waves-light">课表查看</a>
						</li>
						<li class="active"><a
							href="<c:url value='/user/enterList.do'/>"
							class="waves-effect waves-block waves-light">用户管理</a></li>
						<li><a href="<c:url value='/course/enterList.do'/>"
							class="waves-effect waves-block waves-light">课程管理</a></li>
						<li><a href="<c:url value='/teacher/enterList.do'/>"
							class="waves-effect waves-block waves-light">教师管理</a></li>
						<li><a href="<c:url value='/student/enterList.do'/>"
							class="waves-effect waves-block waves-light">学生管理</a></li>
					</ul>
				</div>
				</nav>
				<div class="divider"></div>
				<ul>
					<li><a
						class="waves-effect waves-light btn cyan accent-4 right"
						href="<%=basePath %>/user/enterAdd.do">添加</a></li>
				</ul>
				<ul>
					<li><a
						class="waves-effect waves-light btn cyan accent-4 right"
						href="baseinfo.html">导入数据</a></li>
				</ul>
			</div>
			<div style="padding-top: 35px">
				<div id="table-datatables">
					<table id="data-table-user" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>序号</th>
								<th>用户名</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="item" varStatus="s">
								<tr>
									<td align="center" valign="middle">${s.index+1}</td>
									<td align="center" valign="middle">${item.username}</td>
									<td align="center" valign="middle"><a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/user/enterEdit/${item.id}.do">修改</a>
										&nbsp;&nbsp;&nbsp; <a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/user/deleteUser/${item.id}.do">删除</a>
										&nbsp;&nbsp;&nbsp; <a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/user/enterDetail/${item.id}.do">详细</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--end container--> </section>
		<!-- END CONTENT -->
	</div>
	<!-- END WRAPPER -->
	<!-- END MAIN -->
</body>
</html>