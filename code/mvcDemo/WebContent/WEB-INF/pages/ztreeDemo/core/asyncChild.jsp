<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - asyncChild</title>
<link
	href="<c:url value='/frame/js/plugins/zTree/zTreeStyle/zTreeStyle.css'/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value='/frame/js/plugins/zTree/css/demo.css'/>"
	type="text/css" rel="stylesheet">

<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!-- zTree Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.core-3.5.js'/>"></script>

	<script type="text/javascript">
	<!--
	var url = "<c:url value='/ztreeDemo/asyncData/getChildNodes.do'/>";
	var setting = {
		async: {
			enable: true,
			url:url,
			autoParam:["id", "name=n", "level=lv"],
			otherParam:{"otherParam":"zTreeAsyncTest"},
			contentType : "application/json",
			dataType : "json",
			type : "get",
			dataFilter: filter
		},
		callback: {
			onClick: onClick
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	
	function onClick(event, treeId, treeNode, clickFlag) {
		alert("treeNode.id="+treeNode.id);
	}

	$(document).ready(function(){
		/**
		 * 异步获取根节点数据
		 * @param {Object} idx 科目id
		 */
		var url = "<c:url value='/ztreeDemo/asyncData/getNodeById.do'/>";
		var rootId = 8;
	    var params = {"id":rootId};
	    $.ajax({
			url: url,
			data: params,
			type: 'get',
			async: false,
			dataType :"json",
			success:function(data) {
				data.open=false;
				data.isParent=true;
		    	zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
			},
			error: function () {
	            alert('请求失败');   
	        }
		});
	});
	
	
	//-->
	</script>
</head>
<body>
<h1>点击后异步加载下级节点数据的树</h1>
	<h6>[ 文件路径: ztreeDemo/core/asyncChild.jsp ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>1、setting 配置信息说明</h2>
				<ul class="list">
				<li class="highlight_red">使用异步加载，必须设置 setting.async 中的各个属性，详细请参见 API 文档中的相关内容</li>
				</ul>
			</li>
			<li class="title"><h2>2、treeNode 节点数据说明</h2>
				<ul class="list">
				<li>异步加载功能对于 treeNode 节点数据没有特别要求，如果采用简单 JSON 数据，请设置 setting.data.simple 中的属性</li>
				<li>如果异步加载每次都只返回单层的节点数据，那么可以不设置简单 JSON 数据模式</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>