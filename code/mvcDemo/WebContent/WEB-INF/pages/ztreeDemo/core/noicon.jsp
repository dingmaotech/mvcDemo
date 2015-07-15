<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - noicon</title>
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
			view: {
				showIcon: showIconForTree
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		function showIconForTree(treeId, treeNode) {
			return !treeNode.isParent;
		};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</head>
<body>
	<h1>不显示节点图标的树</h1>
	<h6>[ 文件路径: ztreeDemo/core/noLine.jsp ]</h6>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<ul class="info">
				<li class="title"><h2>1、setting 配置信息说明</h2>
					<ul class="list">
						<li>此 Demo 利用 Function 设置了使父节点不显示图标的规则</li>
						<li class="highlight_red">是否显示节点图标请设置 setting.view.showIcon
							属性，详细请参见 API 文档中的相关内容</li>
					</ul></li>
				<li class="title"><h2>2、treeNode 节点数据说明</h2>
					<ul class="list">
						<li>是否显示图标，不需要 treeNode
							节点数据提供特殊设置，但如果用户需要根据不同节点动态设置，可以对节点数据增加特殊属性，用于判别</li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>