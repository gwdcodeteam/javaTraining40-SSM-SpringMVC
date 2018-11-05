<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../toUpdate">
		<table>
				<tr>
					<td>id</td>
					<td><input type="text" value="${u.id }" name="id"/></td>
				</tr>
				<tr>
					<td>name</td>
					<td><input type="text" value="${u.name }" name="name"/></td>
				</tr>
				<%--  <tr>
					<td>pwd</td>
					<td><input type="text" value="${u.pwd }" name="pwd"/></td>
				</tr> --%>
				<tr>
					<td><input type="submit" value="提交"/></td>
				</tr>
		</table>
	</form>
</body>
</html>