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
								class="mdi-device-now-widgets"></i>学生安排</a>
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
						<div class="col s12 m4 l3 center"><a class="waves-effect waves-light btn cyan lighten-4 black-text" href="baseinfo.html">学生微调</a></div>
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
							class="waves-effect waves-block waves-light">学生微调</a></li>
						<li><a href="#" class="waves-effect waves-block waves-light">课表查看</a>
						</li>
						<li><a href="<c:url value='/user/enterList.do'/>"
							class="waves-effect waves-block waves-light">用户管理</a></li>
						<li><a href="<c:url value='/student/enterList.do'/>"
							class="waves-effect waves-block waves-light">学生管理</a></li>
						<li><a href="<c:url value='/teacher/enterList.do'/>"
							class="waves-effect waves-block waves-light">教师管理</a></li>
						<li class="active"><a
							href="<c:url value='/student/enterList.do'/>"
							class="waves-effect waves-block waves-light">学生管理</a></li>
					</ul>
				</div>
				</nav>
				<!--当前位置 start-->
				<div id="breadcrumbs-wrapper" class="  lighten-3">
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<!-- <h5 class="breadcrumbs-title">学生信息列表</h5> -->
								<ol class="breadcrumb">
									<li><a href="index.html">首页</a></li>
									<li><a href="#">学生管理</a></li>
									<li class="active">学生列表</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--当前位置 end-->
				<div class="divider"></div>
				<ul>

					<li><a
						class="waves-effect waves-light btn green accent-4 right"
						href="<c:url value='/student/exportExcelTemplate.do'/>">导出学生模板</a></li>
					<li><a
						class="waves-effect waves-light btn modal-trigger  green right"
						href="#modal4">导入数据</a></li>
					<li><a
						class="waves-effect waves-light btn green accent-4 right"
						href="<c:url value='/student/exportExcel.do'/>">导出数据</a></li>
					<li><a
						class="waves-effect waves-light btn green accent-4 right"
						href="<c:url value='/student/enterAdd.do'/>">添加</a></li>
				</ul>
			</div>
			<div style="padding-top: 35px">
				<div id="table-datatables">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>序号</th>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>出生年月日</th>
								<th>家庭电话</th>
								<th>家庭地址</th>
								<th>学生电话</th>
								<th>宿舍地址</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${studentList}" var="item" varStatus="s">
								<tr>
									<td align="center" valign="middle">${s.index+1}</td>
									<td align="center" valign="middle">${item.stuNo}</td>
									<td align="center" valign="middle">${item.stuName}</td>
									<td align="center" valign="middle"><c:if
											test="${item.stuSex == '0'}">男生</c:if> <c:if
											test="${item.stuSex == '1'}">女生</c:if></td>
									<td align="center" valign="middle"><fmt:formatDate
											value="${item.birthday}" type="date" dateStyle="default" /></td>
									<td align="center" valign="middle">${item.homeTel}</td>
									<td align="center" valign="middle">${item.homeAddr}</td>
									<td align="center" valign="middle">${item.stuTel}</td>
									<td align="center" valign="middle">${item.dormitoryAddr}</td>
									<td align="center" valign="middle"><a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/student/enterEdit/${item.stuId}.do">修改</a>
										&nbsp;&nbsp;&nbsp; <a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/student/deleteStudent/${item.stuId}.do">删除</a>
										&nbsp;&nbsp;&nbsp; <a
										class="waves-effect waves-light btn cyan accent-4 left"
										href="<%=basePath %>/student/enterDetail/${item.stuId}.do">详细</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div id="modal-fixed-footer">
				<div class="row">
					<div class="col s12 m8 l9">
						<div id="modal4" class="modal  green white-text">
							<div class="modal-content">
								<!--Input File Input-->
								<div id="input-file-input" class="section">
									<h4 class="header">导入数据</h4>
									<div class="row">
										<div class="col s12 m8 l9">
											<form id="importForm" name="importForm"
												action="<c:url value='/student/importExcel.do'/>"
												method="post" enctype="multipart/form-data">
												<div class="file-field input-field">
													<input class="file-path validate" name="filePath"
														id="filePath" type="text" />
													<div class="btn">
														<span>选择</span> <input type="file" name="file" />
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div class="modal-footer green lighten-4">
								<a href="#"
									class="waves-effect waves-green btn-flat modal-action modal-close">关闭</a>
								<a href="javascript:importExcelForm()"
									class="waves-effect waves-red btn-flat modal-action modal-close">导入</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--end container-->
		</section>
		<!-- END CONTENT -->
	</div>
	<!-- END WRAPPER -->
	<!-- END MAIN -->
</body>
<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!--materialize js-->
<script type="text/javascript"
	src="<c:url value='/frame/js/materialize.js'/>"></script>
<!--prism-->
<script type="text/javascript" src="<c:url value='/frame/js/prism.js'/>"></script>
<!-- chartist -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/chartist-js/chartist.min.js'/>"></script>

<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins.js'/>"></script>

<script type="text/javascript">
//表单提交
function importExcelForm(){
    if($.trim($("#filePath").val())==''){
        alert("选择导入的文件！")
        return ;
   }
   $('#importForm').submit(); 
}
</script>
</html>