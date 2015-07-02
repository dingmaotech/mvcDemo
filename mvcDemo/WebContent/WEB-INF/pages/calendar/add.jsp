<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加页面</title>
</head>
<body>
	<fieldset>
		<legend>基本使用</legend>
		<form id="addForm" method="post"
			action="<%=basePath%>/calendar/doSave.do">
			<table class="tableStyle" style="width: 30%;">
				<tr>
					<th colspan="2">表单填写</th>
				</tr>
				<tr>
					<td>日程类型：</td>
					<td width="300"><select name="rclx">
							<option value="1">公司日程</option>
							<option value="2">部门日常</option>
							<option value="3">个人日程</option>
					</select> <span class="star">*</span></td>
				</tr>
				<tr>
					<td>日程主题：</td>
					<td><input type="text" name="title" value="" /><span
						class="star">*</span></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td><input type="text" name="startTime" value="" /><span
						class="star">*</span></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input type="text" name="endTime" value="" /><span
						class="star">*</span></td>
				</tr>
				<tr>
					<td>是否一整天：</td>
					<td><input type="checkbox" name="allday">是<span
						class="star">*</span></td>
				</tr>
				<tr>
					<td>日程状态:</td>
					<td><select name="rczt">
							<option value="1">草稿</option>
							<option value="2">正常</option>
							<option value="3">失效</option>
					</select> <span class="star">*</span><span class="star">*</span></td>
				</tr>
				<tr>
					<td>显示颜色</td>
					<td><input type="text" name="color" value=""><span
						class="star">*</span></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value=" 提 交 " /> <input
						type="reset" value=" 重 置 " class="button" /></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>