<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑页面</title>
</head>
<body>
	<fieldset>
		<legend>基本使用</legend>
		<form id="form1" method="post" action="">
			<table class="tableStyle" style="width: 960px;">
				<tr>
					<th colspan="4">表单填写</th>
				</tr>
				<tr>
					<td>数据名称：</td>
					<td width="300"><input type="text" name="dataName"
						value="${demo.dataName }"
						class="textinput validate[required,length[0,50]]" /><span
						class="star">*</span></td>
					<td>日期选择框：</td>
					<td><input id="render6" type="text" name="createTime"
						value="${demo.createTime }"
						class="date validate[required,custom[date]]" /><span class="star">*</span>
					</td>
				</tr>
				<tr>
					<td>次数:</td>
					<td><input type="text" name="views" value="${demo.views }"
						class="textinput  validate[required,custom[onlyNumber]]" /><span
						class="star">*</span></td>
					<td>类型:</td>
					<td><select id="render1" name="types" value="${demo.types }"
						selectedValue="${demo.types }" class="validate[required]"
						prompt="请选择"></select><span class="star">*</span></td>
				</tr>
				<tr>
					<td>金额:</td>
					<td><input type="text" name="cost" value="${demo.cost }"
						inputMode="positiveDecimal" class="textinput  validate[required]" /><span
						class="star">*</span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td colspan="3"><textarea rows="3" cols="10" name="remark"></textarea></td>
				</tr>
				<tr>
					<td colspan="4" class="ali02"><input type="submit"
						value=" 提 交 " /> <input type="reset" value=" 重 置 " class="button" /></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>