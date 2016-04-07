<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>配置数据库</title>
<script type="text/javascript" src="js/config.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
</head>
<body background="images/b3.jpg">
	<center>
	<h2>配置数据库</h2>
	<hr/>
	<div>
	<form action="changeDatabase" method="post">
		<table>
			<tr>
				<td>请选择你要使用哪一种数据库产品:</td>
				<td>
					<select id="database" name="database">
						<option value="">数据库厂商</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>请选择你要使用哪一个数据库:</td>
				<td>
					<select id="databasename" name="databasename">
						<option value="">数据库名称</option>
					</select>
				</td>
			</tr>
            <tr>
				<td>
					<input type="hidden" name="driver" value="" id="driver"/>
				</td>
				<td>
					<input type="hidden" name="url" value="" id="url"/>
				</td>
			</tr>
			<tr>
				<td>请选择用户名:</td>
				<td>
					<select id="databaseusername" name="databaseusername">
						<option value="">你的用户名</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>请输入密码:</td>
				<td><input type="password" value="" id="databasepassword" name="databasepassword"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="连接数据库" id="connect"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>
	</div>
	</center>
</body>
</html>