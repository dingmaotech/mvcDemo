<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - edit</title>
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
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.excheck-3.5.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.exedit-3.5.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/data/ztreeDemoData.js'/>"></script>

<script type="text/javascript">
var setting = {
		edit: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag
		}
	};

	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	function setEdit() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		remove = $("#remove").attr("checked"),
		rename = $("#rename").attr("checked"),
		removeTitle = $.trim($("#removeTitle").get(0).value),
		renameTitle = $.trim($("#renameTitle").get(0).value);
		zTree.setting.edit.showRemoveBtn = remove;
		zTree.setting.edit.showRenameBtn = rename;
		zTree.setting.edit.removeTitle = removeTitle;
		zTree.setting.edit.renameTitle = renameTitle;
		showCode(['setting.edit.showRemoveBtn = ' + remove, 'setting.edit.showRenameBtn = ' + rename,
			'setting.edit.removeTitle = "' + removeTitle +'"', 'setting.edit.renameTitle = "' + renameTitle + '"']);
	}
	
	function showCode(str) {
		var code = $("#code");
		code.empty();
		for (var i=0, l=str.length; i<l; i++) {
			code.append("<li>"+str[i]+"</li>");
		}
	}
	
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setEdit();
		$("#remove").bind("change", setEdit);
		$("#rename").bind("change", setEdit);
		$("#removeTitle").bind("propertychange", setEdit)
		.bind("input", setEdit);
		$("#renameTitle").bind("propertychange", setEdit)
		.bind("input", setEdit);
	});
</script>
</head>
<body>
<h1>基本 增 / 删 / 改 节点</h1>
<h6>[ 文件路径: ztreeDemo/exedit/edit.jsp ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>1、setting 配置信息说明</h2>
				<ul class="list">
				<li>此 Demo 仅从功能上演示编辑节点的基本方法和配置参数</li>
				<li class="highlight_red">1)、使用 编辑功能，必须设置 setting.edit 中的各个属性，详细请参见 API 文档中的相关内容</li>
				<li class="highlight_red">2)、使用 编辑功能的事件回调函数，必须设置 setting.callback.beforeRemove / onRemove / beforeRename / onRename 等属性，详细请参见 API 文档中的相关内容</li>
				<li class="highlight_red">3)、zTree 不提供默认的增加按钮，如需实现请利用自定义控件的方法 addHoverDom / removeHoverDom ，详细请参见 API 文档中的相关内容；另外也可以参考 "高级 增 / 删 / 改 节点" 的 Demo</li>
				<li><p>基本编辑按钮设置：<br/>
						<input type="checkbox" id="remove" class="checkbox first" checked /><span>显示删除按钮</span>
						<input type="checkbox" id="rename" class="checkbox " checked /><span>显示编辑按钮</span><br/>
						remove 按钮的 title: <input type="text" id="removeTitle" value="remove" /><br/>
						rename 按钮的 title: <input type="text" id="renameTitle" value="rename" />
						<ul id="code" class="log" style="height:85px;"></ul></p>
				</li>
				</ul>
			</li>
			<li class="title"><h2>2、treeNode 节点数据说明</h2>
				<ul class="list">
				<li>对 节点数据 没有特殊要求，用户可以根据自己的需求添加自定义属性</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>