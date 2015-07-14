<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - Simple Data</title>
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
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/data/ztreeDemoData.js'/>"></script>

<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		}
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</head>
<body>
	<h1>最简单的树 -- 简单 JSON 数据</h1>
	<h6>[ 文件路径: ztreeDemo/core/simpleData.jsp ]</h6>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<ul class="info">
				<li class="title"><h2>1、setting 配置信息说明</h2>
					<ul class="list">
						<li class="highlight_red">必须设置 setting.data.simple 内的属性，详细请参见
							API 文档中的相关内容</li>
						<li>与显示相关的内容请参考 API 文档中 setting.view 内的配置信息</li>
						<li>name、children、title 等属性定义更改请参考 API 文档中 setting.data.key
							内的配置信息</li>
					</ul></li>
				<li class="title"><h2>2、treeNode 节点数据说明</h2>
					<ul class="list">
						<li class="highlight_red">简单模式的 JSON 数据需要使用 id / pId
							表示节点的父子包含关系，如使用其他属性设置父子关联关系请参考 setting.data.simple 内各项说明
							<div>
								<pre xmlns="">
									<code>例如：
						var zNodes = [
								{
									"id" : "8",
									"name" : "科目",
									"open" : "true",
									"pId" : "-1",
									"parent" : "false"
								},
								{
									"id" : "9",
									"name" : "语文",
									"open" : "false",
									"pId" : "8",
									"parent" : "false"
								},
								{
									"id" : "19",
									"name" : "人教版",
									"open" : "false",
									"pId" : "9",
									"parent" : "false"
								}];
						</code>
								</pre>
							</div>
						</li>
						<li>默认展开的节点，请设置 treeNode.open 属性</li>
						<li>无子节点的父节点，请设置 treeNode.isParent 属性</li>
						<li>其他属性说明请参考 API 文档中 "treeNode 节点数据详解"</li>
					</ul></li>
				<li class="title"><h2>3、其他说明</h2>
					<ul class="list">
						<li>Demo 中绝大部分都采用简单 JSON 数据模式，以便于大家学习</li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>