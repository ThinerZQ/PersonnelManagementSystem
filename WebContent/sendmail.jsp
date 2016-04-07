<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发送邮件</title>
</head>
<body background="">
	<h2 align="center">发送电子邮件  <a href="manager.jsp">管理用户</a></h2>
	<div align="center">
		<form method="post" action="sendMail?flag=send">
		<table>
			<tr>
				<td>发件人</td>
				<td><input type="text" name="senduser" readonly="readonly" value="${sessionScope.user.email }"></td>
			</tr>
			<tr>
				<td>收件人</td>
				<td><input type="text" name="touser" readonly="readonly" value="${requestScope.sendUser.email }"></td>
			</tr>
			<tr>
				<td>
					主题
				</td>
				<td>
					<input type="text" name="subject" >
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="10" cols="50" name="massage"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="发送">
				</td>
				<td></td>
			</tr>
		</table>
		</form>
	</div>



</body>
</html>