<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理页面</title>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" type="text/css" href="css/register.css"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/manager.js"></script>
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
				<li><a href="department.jsp">部门管理</a></li>
				<c:choose>
					<c:when test="${sessionScope.user.rolename =='总经理' }">
							<li><a href="galary.jsp" target="_blank">薪资管理</a></li>
							<li><a href="backup.jsp" target="_blank">备份</a></li>
							<li><a href="root.jsp" target="_blank">权限管理</a></li>
							<li><a href="dealExcel">导出excel</a></li>
					</c:when>
					
				</c:choose>
			</ul>
		</div>
		<div  class="content">
			<c:choose>
				<c:when test="${sessionScope.user.rolename =='总经理' }">
					<div>
						<input type="button" name="select" id="select" style="background-image: url('images/select.jpg');border: 0;width: 197px;height: 42px;">
					</div>
				</c:when>
				<c:otherwise>
					<c:out value="你没有足够的权限进行查询操作"></c:out>
				</c:otherwise>
			</c:choose>
			
			
    		
			
			<!-- 
			<c:choose>
				<c:when test="${empty requestScope.selectUser }">
					<c:set scope="page" var="display" value="none"></c:set>
				</c:when>
				<c:otherwise>
					<c:set scope="page" var="display" value="block"></c:set>
				</c:otherwise>
			</c:choose>
			 -->
			<div id="shwoselectinfo" style="display:none;">
				<select id="selconditon">
        			<option value="0">请选择你的查询条件</option>
        			<option value="user_id">员工号</option>
         			<option value="realname">员工名字</option>
         			 <option value="username">用户名</option>
          			 <option value="email">电子邮件</option>
           			<option value="age">年龄</option>
           		 	<option value="sex">性别</option>
          			 <option value="registerdate">注册时间</option>
            		<option value="department">部门</option>
    			</select>
			<div>
				<form action="" id="select_div">
					
				</form>
			</div>
			<div>
				<input type="button" value="查找" id="select_input"/>
			</div>
				<table id="information" border="1" style="overflow: scroll;">
					<tr>
						
					</tr>
				
				</table>
				<!-- 
				<form action="selectUserInfo?flag=select" method="post">
					<span>请输入名字<input type="text" style="width: 100px" name="username1"></span>
					<span>
						性别
						<select name="sex1">
							<option value="0">--选择性别--</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</span>
					<span>
						电子邮件:
						<input type="text" style="width: 100px" name="email1">
					</span>
					<span>
						部门名称
						<select name="department1" id="department1">
							<option value="0">--选择部门--</option>
						</select>
					</span>
					<span><input type="submit" value="查　　找"/></span>
				</form>
				 -->
				 <!-- 
				<div>
					<table class="imformation">
						<tr style="background-color:#CCE8CF ;font-size: 20px">
							<td width="100">员工姓名</td>
							<td width="100">别名</td>
							<td width="60">性别</td>
							<td width="60">年龄</td>
							<td width="100">入职时间</td>
							<td width="200">电子邮件</td>
							<td width="100">密码</td>
							<td width="100">部门名称</td>
						</tr>
						<c:forEach items="${requestScope.selectUser }" var="user" varStatus="s" >
							<c:choose>
		   							<c:when test="${s.count mod 2 == 0}">
		   								<tr style="background-color: pink">
		   							</c:when>
		   							<c:otherwise>
		   								<tr>
		   							</c:otherwise>
		   					</c:choose>
							<td><a href="updelUser?user_id=${user.id }"><c:out value="${user.realname }"></c:out></a></td>
							<td><c:out value="${user.username }"></c:out></td>
							<td><c:out value="${user.sex }"></c:out></td>
							<td><c:out value="${user.age }"></c:out></td>
							<td><c:out value="${user.registerDate }"></c:out></td>
							<td><c:out value="${user.email }"></c:out></td>
							<td><c:out value="${user.password }"></c:out></td>
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
									<a href="selectUserInfo?flag=fenye&pageNow=${page }" style="margin-right: 20px;">
										<c:out value="${page }"></c:out>
									</a>
								</c:forEach>
							</td>
						</tr>
					</table>
				
				</div>
				 -->
			</div>
			<div>
			
			<c:choose>
				<c:when test="${sessionScope.user.rolename =='总经理' }">
					<input type="button" name="add" id="add"style="background-image: url('images/add.jpg');border: 0;width: 170px;height: 35px;">
				</c:when>
				<c:otherwise>
					<c:out value="你没有足够的权限进行添加操作"></c:out>
				</c:otherwise>
			</c:choose>
			
			
			
			<div style="display: none" id="showaddinfo">
				<form id="form1">
				<table>
					<tr>
						<td>部　　门:</td>
						<td>
							<select name="department" id="department">
								<option value="0">请选择所属的部门</option>
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
					
				</table>
			</form>
			<input type="submit" value="添加" name="submit" id="ok"/>
			</div>
		</div>
	</div>
</body>
</html>