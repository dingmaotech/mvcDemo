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

<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!-- data-tables -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/data-tables/js/jquery.dataTables.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/data-tables/data-tables-script.js'/>"></script>


<%-- <script type="text/javascript"
	src="<c:url value='/scripts/user/user.js'/>"></script> --%>
<script type="text/javascript">
$(document).ready(function() {
	
    // DataTable
    var usertable = $('#data-table-user').DataTable({
        "bProcessing": true,
  	    "bServerSide":true,//服务端处理分页
  	    "sAjaxSource": "<c:url value='/user/list.do'/>?ran="+Math.random(),
  	    'bPaginate': true,  //是否分页。
  	    "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
  	    'bFilter': true,  //是否使用内置的过滤功能
  	    'bLengthChange': true, //是否允许自定义每页显示条数.
  	    'iDisplayLength':10, //每页显示10条记录
  	    "sPaginationType": "full_numbers", //分页样式   full_numbers
  	    "aoColumns": [
  	    { "sClass": "center", "sName": "id","mDataProp": "id" ,"bVisible": false, "bSearchable": false, "bStorable": false},
  	    { "sClass": "center", "sName": "username","mDataProp": "username", "bSearchable": true, "bStorable": true} , 
  	    { //自定义列
  	    "sName": "id",
  	    "mDataProp": "id" ,
  	    "sClass": "center",
  	    "bSearchable": false,
  	    "bStorable": false,
  	    } ]	
  	    ,"columnDefs": [{
			// 定义操作列
			"targets":3,
			"data": null,
			"render": function(data, type, row) {
				var html = '<a href="javascript:void(0);" class="delete btn btn-default btn-xs"><i class="fa fa-times"></i> 删除</a>'
				html += ' <a href="javascript:void(0);" class="edit btn btn-default btn-xs"><i class="fa fa-arrow-up"></i> 修改</a>'
				html += ' <a href="javascript:void(0);" class="detail btn btn-default btn-xs"><i class="fa fa-arrow-down"></i>详细</a>'
				return html;
			}
		}],
        language: {
            "sProcessing":   "处理中...",
            "sLengthMenu":   "_MENU_ 条结果",
            "sZeroRecords":  "没有匹配结果",
            "sInfo":         "第 _START_ 条至第 _END_条 ，共 _TOTAL_ 条",
            "sInfoEmpty":    "第 0 条至 0 条，共 0 条",
            "sInfoFiltered": "(由 _MAX_ 条结果过滤)",
            "sInfoPostFix":  "",
            "sSearch":       "搜索:",
            "sUrl":          "",
            "sEmptyTable":     "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands":  ",",
            "oPaginate": {
                "sFirst":    "首页",
                "sPrevious": "上页",
                "sNext":     "下页",
                "sLast":     "末页"
            },
            "oAria": {
                "sSortAscending":  ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
 
/* 	  usertable.on( 'order.dt search.dt', function () {
		  usertable.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
		  	cell.innerHTML = i+1;
		  });
	 }).draw(); */
	
 	 // 初始化刪除按钮
/**
 * 	
 	$('#data-table-user tbody').on('click','a.delete',function(e) {
		e.preventDefault();
		if (confirm("确定要删除该属性？")) {
			usertable.row($(this).parents('tr')).remove().draw();
		}
	});

	// 初始化修改按钮
	$('#data-table-user tbody').on('click','a.edit',function(e) {
		e.preventDefault();
		window.top.location.href = "<c:url value='/loginAction/enterLogin.do'/>";
	});

	// 初始化详细按钮
	$('#data-table-user tbody') .on( 'click', 'a.detail', function(e) {
		e.preventDefault();
		window.top.location.href = "<c:url value='/loginAction/enterLogin.do'/>";
	}); 
 */		
		
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
									<li><a href="#">基础信息</a></li>
								</ul>
							</div></li>
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
					<nav class="red">
					<div class="nav-wrapper">
						<ul class="left hide-on-med-and-down">
							<li><a href="javascript:void(0);"
								class="waves-effect waves-block waves-light">基础信息</a></li>
							<li><a href="../../page/rule/rule1.html"
								class="waves-effect waves-block waves-light">规则设置</a></li>
							<li><a href="javascript:void(0);"
								class="waves-effect waves-block waves-light">课程微调</a></li>
							<li><a href="#" class="waves-effect waves-block waves-light">课表查看</a>
							</li>
							<li class="active"><a
								href="<c:url value='/user/enterList.do'/>"
								class="waves-effect waves-block waves-light">用户管理</a></li>
							<li><a href="<c:url value='/course/enterList.do'/>"
								class="waves-effect waves-block waves-light">课程管理</a></li>
							<li><a href="<c:url value='/user/enterList.do'/>"
								class="waves-effect waves-block waves-light">教师管理</a></li>
							<li><a href="<c:url value='/student/enterList.do'/>"
								class="waves-effect waves-block waves-light">学生管理</a></li>
						</ul>
					</div>
					</nav>
					<!--当前位置 start-->
					<div id="breadcrumbs-wrapper" class="  lighten-3">
						<div class="container">
							<div class="row">
								<div class="col s12 m12 l12">
									<ol class="breadcrumb">
										<li><a href="index.html">首页</a></li>
										<li><a href="<c:url value='/user/enterList.do'/>">用户管理</a></li>
										<li class="active">用户列表</li>
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
							href="<c:url value='/user/exportExcelTemplate.do'/>">导出用户模板</a></li>
						<li><a
							class="waves-effect waves-light btn modal-trigger accent-4  green right"
							href="#userModal">导入数据</a></li>
						<li><a
							class="waves-effect waves-light btn green accent-4 right"
							href="<c:url value='/user/exportExcel.do'/>">导出数据</a></li>
						<li><a
							class="waves-effect waves-light btn green accent-4 right"
							href="<c:url value='/user/enterAdd.do'/>">添加</a></li>
					</ul>
				</div>
				<div style="padding-top: 35px">
					<div id="table-datatables">
						<table id="data-table-user" class="responsive-table display"
							cellspacing="0">
							<thead>
								<tr class="green white-text">
									<th>序号</th>
									<th>用户名</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<%-- <c:forEach items="${userList}" var="item" varStatus="s">
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
								</c:forEach> --%>
							</tbody>
							<tfoot>
								<tr class="modal  green white-text">
									<th>序号</th>
									<th>用户名</th>
									<th>操作</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
			<div id="modal-fixed-footer">
				<div class="row">
					<div class="col s12 m8 l9">
						<div id="userModal" class="modal  green white-text">
							<div class="modal-content">
								<!--Input File Input-->
								<div id="input-file-input" class="section">
									<h4 class="header">导入用户数据</h4>
									<div class="row">
										<div class="col s12 m8 l9">
											<form id="importForm" name="importForm"
												action="<c:url value='/user/importExcel.do'/>" method="post"
												enctype="multipart/form-data">
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
			<!--end container--> </section>
			<!-- END CONTENT -->
		</div>
		<!-- END WRAPPER -->
	</div>
	<!-- END MAIN -->
</body>

<!--materialize js-->
<script type="text/javascript"
	src="<c:url value='/frame/js/materialize.js'/>"></script>
<!--prism-->
<script type="text/javascript" src="<c:url value='/frame/js/prism.js'/>"></script>

<!--plugins.js - Some Specific JS codes for Plugin Settings-->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins.js'/>"></script>

<script type="text/javascript">
//表单提交
function importExcelForm(){
    if($.trim($("#filePath").val())==''){
        alert("选择导入的文件！");
        return ;
   }
   $('#importForm').submit(); 
}
</script>
</html>