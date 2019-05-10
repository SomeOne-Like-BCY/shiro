<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
登录页面
<form action="http://127.0.0.1:18080/shiro/user/loginCheck" method="post">
    用户名:<br>
    <input type="text" name="username" value=""><br>
   密码:<br>
    <input type="text" name="password" value=""><br><br>
    <input type="submit" value="提交">
</form>



</body>
</html>