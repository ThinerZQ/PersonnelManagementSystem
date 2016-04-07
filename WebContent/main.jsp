<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Iutf-8">
<title>企业人事管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" type="text/css" href="css/register.css"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<script type="text/javascript" src="js/personalinfo.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
</head>
<body>
	<div class="main">
		<div class="header">
			<a href="#" class="logo">企业人事管理系统</a>
			<div class="my_info">
				<a href="personalinfo.jsp"  class="login_success">欢迎你,
					<c:out value="${sessionScope.user.username }"></c:out>
				</a>
				<!-- <span class="login_realname">
					<c:out value="${sessionScope.user.realname} "></c:out>
				</span> -->
				<img alt="${sessionScope.user.username }" src="<%=request.getContextPath() %>/${sessionScope.user.photo}" />
			</div>
		</div>
		<div class="navgation">
			<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="manager.jsp">人事管理</a></li>
				<li><a href="department.jsp">部门信息</a></li>
			</ul>
		</div>
		<div  class="content">
		<!-- 
			<form action="" method="post">
				<span>请输入名字<input type="text" style="width: 100px"></span>
				<span>
					性别
					<select>
						<option value="0">--选择性别--</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</span>
				<span>
					电子邮件:
					<input type="text" style="width: 100px">
				</span>
				<span>
					部门名称
					<select name="department" id="department">
						<option value="0">--选择部门--</option>
					</select>
				</span>
				<span><input type="submit" value="查　　找"/></span>
			</form>
			 -->
			
			<table class="imformation">
				<tr style="background-color:#CCE8CF ;font-size: 20px">
					<td width="100"><a href="dealFenye?flag=fenye&pageNow=1&sortway=realname">员工姓名</a></td>
					<td width="100"><a href="dealFenye?flag=fenye&pageNow=1&sortway=username">别名</a></td>
					<td width="60"><a href="dealFenye?flag=fenye&pageNow=1&sortway=sex">性别</a></td>
					<td width="60"><a href="dealFenye?flag=fenye&pageNow=1&sortway=age">年龄</a></td>
					<td width="100"><a href="dealFenye?flag=fenye&pageNow=1&sortway=registerdate">入职时间</a></td>
					<td width="200"><a href="dealFenye?flag=fenye&pageNow=1&sortway=email">电子邮件</a></td>
					
					<td width="100"><a href="dealFenye?flag=fenye&pageNow=1&sortway=name">部门名称</a></td>
				</tr>
				<c:forEach items="${requestScope.userInfoList }" var="user" varStatus="s" >
					<c:choose>
   							<c:when test="${s.count mod 2 == 0}">
   								<tr style="background-color: pink">
   							</c:when>
   							<c:otherwise>
   								<tr>
   							</c:otherwise>
   					</c:choose>
					<td><c:out value="${user.realname }"></c:out></td>
					<td><c:out value="${user.username }"></c:out></td>
					<td><c:out value="${user.sex }"></c:out></td>
					<td><c:out value="${user.age }"></c:out></td>
					<td><c:out value="${user.registerDate }"></c:out></td>
					<td><c:out value="${user.email }"></c:out></td>
					
					<td>
						<c:out value="${user.departmentName }"></c:out>
					</td>
					</tr>
					<c:if test="${s.last }">
						<c:set scope="page" var="pageCount" value="${s.count }"></c:set>
					</c:if>
					
				</c:forEach>
				<tr>
					<td colspan="8" align="center" style="font-size: 20px;">
						
						<c:forEach items="${requestScope.pageCountList }" var="page">
							<a href="dealFenye?flag=fenye&pageNow=${page }&mark=1" style="margin-right: 20px;">
								<c:out value="${page }"></c:out>
							</a>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>