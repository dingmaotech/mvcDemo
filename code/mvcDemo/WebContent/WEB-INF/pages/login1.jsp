<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html public "-//w3c//dtd html 4.01 transitional//en">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教学管理系统</title>
<script language=javascript>
//设置登陆的跳转页面
function fsubmit(){
   var userName = document.form1.username.value;
   if(userName==''){
        alert("请输入用户名信息！")
        return ;
   }
   var password = document.form1.password.value;
      if(password==''){
        alert("请输入密码！")
        return ;
   }
    document.form1.submit();
}
/**
*初始化页面时，将光标放到用户名框中
*/
window.onload = function(){
	var oInput = document.getElementById("userName");
		oInput.focus();
		//绑定回车事件
			document.body.onkeydown = function(event){
		var evt=event?event:window.event;
		if(13==evt.keyCode){
              fsubmit();
		}
	}
}
	

</script>
<style type="text/css">
* {
	overflow: hidden;
	font-size: 9pt;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(<%=path %>/frame/images/bg.gif);
	background-repeat: repeat-x;
}
</style>

</head>

<body>
	<form name="form1" action="<%=path %>/loginAction/login.do"
		method="post" id="form1">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="561"><table width="940" border="0"
									align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="80">&nbsp;</td>
									</tr>
									<tr>
										<td height="190"><table width="100%" border="0"
												cellspacing="0" cellpadding="0">
												<tr>
													<td width="206" height="365">&nbsp;</td>
													<td width="650"
														style="background:url(<%=path %>/images/login.jpg)">
														<table width="280" border="0" align="right"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="24" height="120"></td>
																<td width="37" height="120"></td>
																<td height="120" colspan="2"></td>
															</tr>
															<tr>
																<td width="24" height="40"><img
																	src="<%=path %>/frame/images/user.gif"></td>
																<td width="37" height="40">用 户</td>
																<td height="40" colspan="2"><input class="txt1"
																	size="22" type="text" value="" id="userName"
																	name="username" /></td>
															</tr>
															<tr>
																<td width="24" height="40"><img
																	src="<%=path %>/frame/images/password.gif"></td>
																<td width="37" height="40">密 码</td>
																<td height="40" colspan="2"><input class="txt1"
																	size="22" type="password" value="" id="password"
																	name="password"></td>
															</tr>
															<tr>
																<td height="30">&nbsp;</td>
																<td height="30">&nbsp;</td>
																<td width="90" height="40"><a
																	href="javascript:fsubmit();"><img
																		src="<%=path %>/frame/images/login.gif" border="0"
																		style="cursor: pointer;" /></a></td>
															</tr>
														</table>
													</td>
													<td width="214">&nbsp;</td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td height="133">&nbsp;</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>
