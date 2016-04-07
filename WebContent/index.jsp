<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>企业人事管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
</head>
<body>

	<div class="main">
		<div class="header">
			<a href="#" class="logo">企业人事管理系统</a>
			<a href="main.jsp" class="login_success">
			<c:choose>
				<c:when test="${empty sessionScope.user.username  }">
					
				</c:when>
				<c:otherwise>
					<c:out value="${sessionScope.user.username }， 你已经成功登录，点击进入"></c:out>
				</c:otherwise>
			</c:choose>
		
			</a>
			<span><a href="goLoginServlet" class="login">登陆</a></span>
			<span><a href="register.jsp"  class="register">注册</a></span>
		</div>
		<div class="content">
			<div class="left_content">
				
			</div>
			<div class="right_content">
				
			</div>
		</div>
		<div class="foot">
		
		</div>
	</div>



<h4></h4>

</body>
</html>