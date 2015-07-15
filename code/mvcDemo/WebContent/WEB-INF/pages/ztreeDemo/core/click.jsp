<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO - click</title>
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
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};
	var log, className = "dark";
	function beforeClick(treeId, treeNode, clickFlag) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
		return (treeNode.click != false);
	}
	function onClick(event, treeId, treeNode, clickFlag) {
		alert(treeNode.id);
		showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
	}		
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds();
		return (h+":"+m+":"+s);
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
</head>
<body>
	<h1>单击节点控制</h1>
	<h6>[ 文件路径: ztreeDemo/core/click.html ]</h6>
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<ul class="info">
				<li class="title"><h2>1、beforeClick / onClick 事件回调函数控制</h2>
					<ul class="list">
						<li>利用 click 事件回调函数 可以进行各种其他的附加操作，这里简单演示如何监控此事件</li>
						<li><p>
								<span class="highlight_red">请尝试按下 <b>Ctrl</b> 或 <b>Cmd</b>
									键进行 多节点选择 和 取消选择
								</span><br /> click log:<br />
							<ul id="log" class="log"></ul>
							</p></li>
					</ul></li>
				<li class="title"><h2>2、setting 配置信息说明</h2>
					<ul class="list">
						<li class="highlight_red">需要设置 setting.callback.beforeClick 和
							setting.callback.onClick 属性, 详细请参见 API 文档中的相关内容</li>
					</ul></li>
				<li class="title"><h2>3、treeNode 节点数据说明</h2>
					<ul class="list">
						<li>对 节点数据 没有特殊要求，用户可以根据自己的需求添加自定义属性</li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>