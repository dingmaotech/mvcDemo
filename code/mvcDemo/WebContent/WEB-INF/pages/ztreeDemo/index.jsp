<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZTREE DEMO</title>
<link
	href="<c:url value='/frame/js/plugins/zTree/zTreeStyle/zTreeStyle.css'/>"
	type="text/css" rel="stylesheet" >
<!-- jQuery Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/jquery-1.11.2.min.js'/>"></script>
<!-- zTree Library -->
<script type="text/javascript"
	src="<c:url value='/frame/js/plugins/zTree/js/jquery.ztree.all-3.5.js'/>"></script>
	
</head>
<script type="text/javascript">
<!--
var zTree;
var demoIframe;

var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file);
				return true;
			}
		}
	}
};

var zNodes =[
	{id:1, pId:0, name:"[core] 基本功能 演示", open:true},
	{id:101, pId:1, name:"最简单的树 --  标准 JSON 数据", file:"<c:url value='/ztreeDemo/core/standardData.do'/>"},
	{id:102, pId:1, name:"最简单的树 --  简单 JSON 数据", file:"<c:url value='/ztreeDemo/core/simpleData.do'/>"},
	{id:103, pId:1, name:"不显示 连接线", file:"<c:url value='/ztreeDemo/core/noline.do'/>"},
	{id:104, pId:1, name:"不显示 节点 图标", file:"<c:url value='/ztreeDemo/core/noicon.do'/>"},
	{id:105, pId:1, name:"自定义图标 --  icon 属性", file:"<c:url value='/ztreeDemo/core/custom_icon.do'/>"},
	{id:106, pId:1, name:"自定义图标 --  iconSkin 属性", file:"<c:url value='/ztreeDemo/core/custom_iconSkin.do'/>"},
	{id:107, pId:1, name:"自定义字体", file:"/ztreeDemo/core/custom_font"},
	{id:115, pId:1, name:"超链接演示", file:"/ztreeDemo/core/url"},
	{id:108, pId:1, name:"异步加载全部 节点数据", file:"<c:url value='/ztreeDemo/core/async.do'/>"},
	{id:115, pId:1, name:"异步加载下一级 节点数据", file:"<c:url value='/ztreeDemo/core/asyncChild.do'/>"},
	{id:109, pId:1, name:"用 zTree 方法 异步加载 节点数据", file:"/ztreeDemo/core/async_fun"},
	{id:110, pId:1, name:"用 zTree 方法 更新 节点数据", file:"/ztreeDemo/core/update_fun"},
	{id:111, pId:1, name:"单击 节点 控制", file:"<c:url value='/ztreeDemo/core/click.do'/>"},
	{id:112, pId:1, name:"展开 / 折叠 父节点 控制", file:"/ztreeDemo/core/expand"},
	{id:113, pId:1, name:"根据 参数 查找 节点", file:"/ztreeDemo/core/searchNodes"},
	{id:114, pId:1, name:"其他 鼠标 事件监听", file:"/ztreeDemo/core/otherMouse"},

	{id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
	{id:201, pId:2, name:"Checkbox 勾选操作", file:"<c:url value='/ztreeDemo/excheck/checkbox.do'/>"},
	{id:206, pId:2, name:"Checkbox nocheck 演示", file:"/ztreeDemo/excheck/checkbox_nocheck"},
	{id:207, pId:2, name:"Checkbox chkDisabled 演示", file:"/ztreeDemo/excheck/checkbox_chkDisabled"},
	{id:208, pId:2, name:"Checkbox halfCheck 演示", file:"/ztreeDemo/excheck/checkbox_halfCheck"},
	{id:202, pId:2, name:"Checkbox 勾选统计", file:"/ztreeDemo/excheck/checkbox_count"},
	{id:203, pId:2, name:"用 zTree 方法 勾选 Checkbox", file:"/ztreeDemo/excheck/checkbox_fun"},
	{id:204, pId:2, name:"Radio 勾选操作", file:"/ztreeDemo/excheck/radio"},
	{id:209, pId:2, name:"Radio nocheck 演示", file:"/ztreeDemo/excheck/radio_nocheck"},
	{id:210, pId:2, name:"Radio chkDisabled 演示", file:"/ztreeDemo/excheck/radio_chkDisabled"},
	{id:211, pId:2, name:"Radio halfCheck 演示", file:"/ztreeDemo/excheck/radio_halfCheck"},
	{id:205, pId:2, name:"用 zTree 方法 勾选 Radio", file:"/ztreeDemo/excheck/radio_fun"},

	{id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
	{id:301, pId:3, name:"拖拽 节点 基本控制", file:"<c:url value='/ztreeDemo/exedit/drag.do'/>"},
	{id:302, pId:3, name:"拖拽 节点 高级控制", file:"/ztreeDemo/exedit/drag_super"},
	{id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点", file:"/ztreeDemo/exedit/drag_fun"},
	{id:304, pId:3, name:"基本 增 / 删 / 改 节点", file:"<c:url value='/ztreeDemo/exedit/edit'/>"},
	{id:305, pId:3, name:"高级 增 / 删 / 改 节点", file:"/ztreeDemo/exedit/edit_super"},
	{id:306, pId:3, name:"用 zTree 方法 增 / 删 / 改 节点", file:"/ztreeDemo/exedit/edit_fun"},
	{id:307, pId:3, name:"异步加载 & 编辑功能 共存", file:"/ztreeDemo/exedit/async_edit"},
	{id:308, pId:3, name:"多棵树之间 的 数据交互", file:"/ztreeDemo/exedit/multiTree"},

	{id:4, pId:0, name:"大数据量 演示", open:false},
	{id:401, pId:4, name:"一次性加载大数据量", file:"/ztreeDemo/bigdata/common"},
	{id:402, pId:4, name:"分批异步加载大数据量", file:"/ztreeDemo/bigdata/diy_async"},
	{id:403, pId:4, name:"分批异步加载大数据量", file:"/ztreeDemo/bigdata/page"},

	{id:5, pId:0, name:"组合功能 演示", open:false},
	{id:501, pId:5, name:"冻结根节点", file:"/ztreeDemo/super/oneroot"},
	{id:502, pId:5, name:"单击展开/折叠节点", file:"/ztreeDemo/super/oneclick"},
	{id:503, pId:5, name:"保持展开单一路径", file:"/ztreeDemo/super/singlepath"},
	{id:504, pId:5, name:"添加 自定义控件", file:"/ztreeDemo/super/diydom"},
	{id:505, pId:5, name:"checkbox / radio 共存", file:"/ztreeDemo/super/checkbox_radio"},
	{id:506, pId:5, name:"左侧菜单", file:"/ztreeDemo/super/left_menu"},
	{id:513, pId:5, name:"OutLook 样式的左侧菜单", file:"/ztreeDemo/super/left_menuForOutLook"},
	{id:507, pId:5, name:"下拉菜单", file:"/ztreeDemo/super/select_menu"},
	{id:509, pId:5, name:"带 checkbox 的多选下拉菜单", file:"/ztreeDemo/super/select_menu_checkbox"},
	{id:510, pId:5, name:"带 radio 的单选下拉菜单", file:"/ztreeDemo/super/select_menu_radio"},
	{id:508, pId:5, name:"右键菜单 的 实现", file:"/ztreeDemo/super/rightClickMenu"},
	{id:511, pId:5, name:"与其他 DOM 拖拽互动", file:"/ztreeDemo/super/dragWithOther"},
	{id:512, pId:5, name:"异步加载模式下全部展开", file:"/ztreeDemo/super/asyncForAll"},

	{id:6, pId:0, name:"其他扩展功能 演示", open:false},
	{id:601, pId:6, name:"隐藏普通节点", file:"/ztreeDemo/exhide/common"},
	{id:602, pId:6, name:"配合 checkbox 的隐藏", file:"/ztreeDemo/exhide/checkbox"},
	{id:603, pId:6, name:"配合 radio 的隐藏", file:"/ztreeDemo/exhide/radio"}
];

$(document).ready(function(){
	var t = $("#tree");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.selectNode(zTree.getNodeByParam("id", 101));

});

function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
	htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
	maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
	h = demoIframe.height() >= maxH ? minH:maxH ;
	if (h < 530) h = 530;
	demoIframe.height(h);
}

//-->
</script>
<body>
<table width="100%"  border=0 height=600px align=left>
	<tr>
		<td width="20%" align="left" valign="top" style="border-right: #999999 1px dashed" >
			<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
		</td>
		<td>
			<iframe id="testIframe"  name="" frameborder="0" scrolling="auto"  width="100%"  height="600px"  src="<c:url value='/ztreeDemo/core/standardData.do'/>"></iframe>
		</td>
	</tr>
</table>
</body>
</html>