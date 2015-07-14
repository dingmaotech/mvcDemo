<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - checkbox</title>
<link
	href="<c:url value='/frame/js/plugins/zTree/zTreeStyle/zTreeStyle.css'/>"
	type="text/css" rel="stylesheet">
<link href="<c:url value='/frame/js/plugins/zTree/css/demo.css'/>"
	type="text/css" rel="stylesheet">

<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!-- script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery-1.4.4.min.js'/>"></script-->
	
	
<!-- zTree Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.core-3.5.js'/>"></script>

<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.excheck-3.5.js'/>"></script>
		
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/data/ztreeDemoData.js'/>"></script>

<script type="text/javascript">
var setting = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "p", "N" : "p" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	var code;
	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var py = $("#py").prop('checked')? "p":"";
		var sy = $("#sy").prop("checked")? "s":"";
		var pn = $("#pn").prop("checked")? "p":"";
		var sn = $("#sn").prop("checked")? "s":"";
		type = { "Y":py + sy, "N":pn + sn};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
	
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
</script>
</head>
<body>
<h1>Checkbox 勾选操作</h1>
<h6>[ 文件路径: ztreeDemo/excheck/checkbox.jsp ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>1、setting 配置信息说明</h2>
				<ul class="list">
				<li class="highlight_red">使用 checkbox，必须设置 setting.check 中的各个属性，详细请参见 API 文档中的相关内容</li>
				<li><p>父子关联关系：<br/>
						被勾选时：<input type="checkbox" id="py" class="checkbox first" checked="checked"  /><span>关联父</span>
						<input type="checkbox" id="sy" class="checkbox first" checked="checked" /><span>关联子</span><br/>
						取消勾选时：<input type="checkbox" id="pn" class="checkbox first" checked="checked" /><span>关联父</span>
						<input type="checkbox" id="sn" class="checkbox first" checked="checked" /><span>关联子</span><br/>
						<ul id="code" class="log" style="height:20px;"></ul></p>
				</li>
				</ul>
			</li>
			<li class="title"><h2>2、treeNode 节点数据说明</h2>
				<ul class="list">
				<li class="highlight_red">1)、如果需要初始化默认节点被勾选，请设置 treeNode.checked 属性，详细请参见 API 文档中的相关内容</li>
				<li class="highlight_red">2)、如果某节点禁用 checkbox，请设置 treeNode.chkDisabled 属性，详细请参见 API 文档中的相关内容 和 'chkDisabled 演示'</li>
				<li class="highlight_red">3)、如果某节点不显示 checkbox，请设置 treeNode.nocheck 属性，详细请参见 API 文档中的相关内容 和 'nocheck 演示'</li>
				<li class="highlight_red">4)、如果更换 checked 属性，请参考 API 文档中 setting.data.key.checked 的详细说明</li>
				<li>5)、其他请参考 API 文档中 treeNode.checkedOld / getCheckStatus / check_Child_State / check_Focus 的详细说明</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>