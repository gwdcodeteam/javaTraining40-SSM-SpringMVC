<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table><tr><td>id</td><td>name</td><td>pwd</td>
				</tr>
				<c:forEach var="u" items="${userList}">
				<tr>
					<td>${u.id}</td>
					<td>${u.name}</td>
					<td>${u.pwd}</td>
					<td><a href="del/${u.id}">删除</a></td>
					<td><a href="add/${u.id}">修改</a></td>
				</tr>
				</c:forEach>
	</table>
	<div>
		<a href="add.jsp">添加员工</a>
	</div>
</body>
</html>