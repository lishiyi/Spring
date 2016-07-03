<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/3 0003
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<sf:form method="post" modelAttribute="user">
    username<sf:input path="username"/><sf:errors path="username"/><br/>
    password<sf:input path="password"/><sf:errors path="password"/><br/>
    nickname<sf:input path="nickname"/><br/>
    email<sf:input path="email"/><sf:errors path="email"/><br/>
    <input type="submit"/>
</sf:form>
</body>
</html>
