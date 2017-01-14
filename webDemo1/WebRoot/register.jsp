<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>欢迎注册，请填写信息</h1>
	<form action="/webDemo1/servlet/RegisterServlet" method="post">
		用户名：<input type="text" name="username"/>${urc.msg.username }${userExist }<br>
		密码：<input type="password" name="password"/>${urc.msg.password }<br>
		重复密码：<input type="password" name="repassword"/>${urc.msg.repassword }<br>
		邮箱：<input type="text" name="email"/>${urc.msg.email }<br>
		生日：<input type="text" name="birthday"/>${urc.msg.birthday }<br>
		<input type="submit" value="提交">
	</form>
</body>
</html>