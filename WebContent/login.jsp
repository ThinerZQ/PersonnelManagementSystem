<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login page</title>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link href="css/login.css" rel="stylesheet" type="text/css"/>

</head>
<body background="images/b1.jpg">
	<center>
	<h3><span style="font-size: 32px;color: white;">用户登录</span><span style="font-size: 22px;margin-left: 100px"><a href="index.jsp" style="color:white;text-decoration: none">回首页</a></span></h3>
	<hr/>
	<div>
	<form action="loginServlet?flag=login" method="post">
		<table>
			<tr>
				<td>
					<span style="color:white;">部　门：</span>
				</td>
				<td>
					<select name="department" id="department" >
						<option value="0">---请选择你的部门---</option>
						<!-- 
						<c:forEach items="${requestScope.departmentList }" var="department" >
							<option value="${department.id }">${department.name }</option>
						</c:forEach>
						 -->
					</select>
				</td>
				<td>
					<span></span>
				</td>
				<td>
					<span></span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="color:white;">名　字：</span>
				</td>
				<td>
					<select name="realname" id="realname">
						<option value="0">---请选择你的姓名---</option>
					</select>
				</td>
				<td>
					<span></span>
				</td>
				<td>
					<span></span>
				</td>
			</tr>
			<tr>
				<td>
					<span style="color:white;">密　码：</span>
				</td>
				<td>
					<input type="password" name="password" id="password" value=""/>
				</td>
				<td>
					<span></span>
				</td>
				<td>
					<span class="errorinfo"><c:out value="${requestScope.loginErrorPassword }"></c:out></span>
				</td>
			</tr>
			<tr>
			<td>
					<span style="color:white;">验证码：</span>
				</td>
				<td>
					<input type="text" name="checkinfo" id="checkinfo" value=""/>
				</td>
				<td>
					<img src="checkInfoServlet" title="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer" onClick="this.src='checkInfoServlet?'+Math.random()" />
				</td>
				<td>
					<span class="errorinfo"><c:out value="${requestScope.loginErrorCheckInfo }"></c:out></span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="登陆" id="ok"/>
				</td>
				<td>
					<span></span>
				</td>
				<td>
					<input type="reset" value="重置"/><a href="config.jsp" style="color:white;" target="_blank">配置数据库</a>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</center>
</body>
</html>