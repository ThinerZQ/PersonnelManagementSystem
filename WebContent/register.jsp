<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/register.js">
	
</script>
</head>
<body>

	<center>
		<div>
			<span>欢迎来到注册页面，请填写下面的注册信息</span>
			<a href="index.jsp" style="margin-left: 200px;">回首页</a>
		</div>
		<hr/>
		<div style="margin-top: 50px;">
			<form action="registerServlet" id="form1" method="post">
				<table border="1">
					<tr>
						<td>部　　门:</td>
						<td>
							<select name="department" id="department">
								<option value="0">请选择你所属的部门</option>
							</select>
						</td>
						<td>
							<span id="department_info"></span>
						</td>
					</tr>
					<tr>
						<td>用户　名:</td>
						<td>
							<input type="text" name="username" id="username">
						</td>
						<td>
							<span id="username_info"></span>
						</td>
					</tr>
					<tr>
						<td>真实姓名:</td>
						<td>
							<input type="text" name="realname" id="realname">
						</td>
						<td>
							<span id="realname_info"></span>
						</td>
					</tr>
					<tr>
						<td>密　　码:</td>
						<td>
							<input type="password" name="password" id="password">
						</td>
						<td>
							<span id="password_info"></span>
						</td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td>
							<input type="password" name="check_password" id="check_password">
						</td>
						<td>
							<span id="check_password_info"></span>
						</td>
					</tr>
					<tr>
						<td>E-mail　:</td>
						<td>
							<input type="text" name="email" id="email">
						</td>
						<td>
							<span id="email_info"></span>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="注册" name="submit" id="ok"/>
						</td>
						<td>
							
						</td>
						<td>
							<input type="reset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</center>
	
	
</body>
</html>