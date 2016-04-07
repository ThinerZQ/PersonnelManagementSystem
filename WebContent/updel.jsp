<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/updel.css"/>
<title>Insert title here</title>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/updel.js"></script>
</head>
<body background="images/b4.jpg">
	<h2 align="center"><span>修改用户信息</span><span style="margin-left: 100px"><a href="manager.jsp">回首页</a></span></h2>
	<form action="updelUser?flag=update" method="post">
	<table align="center">
		<tr>
			<td>员工ID</td>
			<td><input type="text" name="user_id" value="${requestScope.selectUser.id }" readonly="readonly"  ></td>
			<td><a href="delUser?flag=del&user_id=${requestScope.selectUser.id }" id="del">删除此用户</a></td>
		</tr>
		<tr>
			<td>姓　名</td>
			<td><input type="text" name="realname" value="${requestScope.selectUser.realname }" ></td>
		</tr>
		<tr>
			<td>所属部门</td>
			<td><input type="text" name="department" value="${requestScope.selectUser.departmentName }" readonly="readonly"></td>
		</tr>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="username" value="${requestScope.selectUser.username }"></td>
		</tr>
		<tr>
			<td>密　码</td>
			<td><input type="text" name="password" value="${requestScope.selectUser.password }" readonly="readonly"></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><input type="text" name="sex" value="${requestScope.selectUser.sex }"></td>
		</tr>
		<tr>
			<td>年龄</td>
			<td><input type="text" name="age" value="${requestScope.selectUser.age }"></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" value="${requestScope.selectUser.email }" readonly="readonly"></td>
			<td><a href="sendMail?flag=gosendmail&user_id=${requestScope.selectUser.id }">发送邮件</a></td>
		</tr>
		<tr>
			<td>注册时间</td>
			<td><input type="text" name="registerdate" value="${requestScope.selectUser.registerDate }" readonly="readonly"></td>
		</tr>
		<tr>
			<td><input type="submit" value="修改"></td>
			<td><span>${requestScope.info } </span></td>
		</tr>
	</table>
	</form>
</body>
</html>