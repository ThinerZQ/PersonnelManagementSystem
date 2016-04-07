<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/backup.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
</head>
<body>
	
	<div>
		<form action="backup" method="post">
		<div>请选择你要备份的数据库</div>
		<div>
			<select id="database" name="database">
				<option value="0">请选择数据库</option>
				<option value="softwareassignment">softwareassignment</option>
			</select>
		</div>
		<div>请选择要备份的表</div>
		<div>
			<select id="table" multiple="multiple" name="table">
				<option value="0">请选择表</option>
			</select>
		</div>
		<input type="submit" value="提交" id="ok">
		</form>
	</div>
</body>
</html>