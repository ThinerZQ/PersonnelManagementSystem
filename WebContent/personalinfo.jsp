<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的个人信息</title>
<script type="text/javascript" src="js/personalinfo.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
</head>
<body background="images/b2.jpg">
	<div>
		<div style="text-align: center">设置我的个人信息<span  style="margin-left: 100px"><a href="index.jsp">回首页</a></span></div>
		
		<div>
			<hr>
			<div>
				<form action="dealFileUpload" method="post" enctype="multipart/form-data">
					<img src="<%=request.getContextPath() %>/${sessionScope.user.photo}" width="150" height="150"/>
					<input type="file" name="photofile" accept="image/*"/><br>
					<input type="submit" value="提交"/><span>${requestScope.info }</span>
				</form>
			</div>
			
			<div>
				<form action="updateInfo" method="post">
				<table>
					<tr>
						<td></td>
						<td><input type="hidden" value="${sessionScope.user.id }" name="user_id"></td>
						<td></td>
					</tr>
					<tr>
						<td>姓名：</td>
						<td><input type="text" disabled="disabled" value="${sessionScope.user.realname }" name="realname" id="realname"></td>
						<td></td>
					</tr>
					<tr>
						<td>网名：</td>
						<td><input type="text" value="${sessionScope.user.username }" name="username" id="username"/></td>
						<td><a id="username_info" style="display: none">用户名在2-10个字符之间</a></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" value="" name="password" id="password"></td>
						<td><a id="password_info" style="display: none">你的密码太过简单了，至少六位</a></td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>
							<input type="radio" value="男" ${sessionScope.user.sex =="男"?"checked":"" } name="sex">男<input type="radio" value="女" ${sessionScope.user.sex =="女"?"checked":"" } name="sex">女
							
						</td>
						<td></td>
					</tr>
					<tr>
						<td>年龄：</td>
						<td><input type="text" value="${sessionScope.user.age }" name="age" id="age"></td>
						<td><a id="age_info" style="display: none">请输入一个合适的年龄</a></td>
					</tr>
					<tr>
						<td><input type="submit" value="修改" id="update"></td>
						<td>
							${requestScope.updateInfo }
						</td>
						<td><input type="reset" value="重置"/></td>
						
					</tr>
					
				</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>