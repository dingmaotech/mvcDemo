<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>ZTREE DEMO - Standard Data</title>
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
	var setting = {};

	var zNodes = [ {
		name : "父节点1 - 展开",
		open : true,
		children : [ {
			name : "父节点11 - 折叠",
			children : [ {
				name : "叶子节点111"
			}, {
				name : "叶子节点112"
			}, {
				name : "叶子节点113"
			}, {
				name : "叶子节点114"
			} ]
		}, {
			name : "父节点12 - 折叠",
			children : [ {
				name : "叶子节点121"
			}, {
				name : "叶子节点122"
			}, {
				name : "叶子节点123"
			}, {
				name : "叶子节点124"
			} ]
		}, {
			name : "父节点13 - 没有子节点",
			isParent : true
		} ]
	}, {
		name : "父节点2 - 折叠",
		children : [ {
			name : "父节点21 - 展开",
			open : true,
			children : [ {
				name : "叶子节点211"
			}, {
				name : "叶子节点212"
			}, {
				name : "叶子节点213"
			}, {
				name : "叶子节点214"
			} ]
		}, {
			name : "父节点22 - 折叠",
			children : [ {
				name : "叶子节点221"
			}, {
				name : "叶子节点222"
			}, {
				name : "叶子节点223"
			}, {
				name : "叶子节点224"
			} ]
		}, {
			name : "父节点23 - 折叠",
			children : [ {
				name : "叶子节点231"
			}, {
				name : "叶子节点232"
			}, {
				name : "叶子节点233"
			}, {
				name : "叶子节点234"
			} ]
		} ]
	}, {
		name : "父节点3 - 没有子节点",
		isParent : true
	}

	];

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</head>

<body>
	<h1>最简单的树 -- 标准 JSON 数据</h1>
	<h6>[ 文件路径: ztreeDemo/core/standardData.jsp ]</h6>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<ul class="info">
				<li class="title"><h2>1、setting 配置信息说明</h2>
					<ul class="list">
						<li class="highlight_red">普通使用，无必须设置的参数</li>
						<li>与显示相关的内容请参考 API 文档中 setting.view 内的配置信息</li>
						<li>name、children、title 等属性定义更改请参考 API 文档中 setting.data.key
							内的配置信息</li>
					</ul></li>
				<li class="title"><h2>2、treeNode 节点数据说明</h2>
					<ul class="list">
						<li class="highlight_red">标准的 JSON 数据需要嵌套表示节点的父子包含关系
							<div>
								<pre xmlns="">
									<code>例如：
var nodes = [
	{name: "父节点1", children: [
		{name: "子节点1"},
		{name: "子节点2"}
	]}
];
</code>
								</pre>
							</div>
						</li>
						<li>默认展开的节点，请设置 treeNode.open 属性</li>
						<li>无子节点的父节点，请设置 treeNode.isParent 属性</li>
						<li>其他属性说明请参考 API 文档中 "treeNode 节点数据详解"</li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>