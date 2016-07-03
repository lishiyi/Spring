<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/3 0003
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css">
    <title>Insert title here</title>
</head>
<body>
${loginUser.nickname}----${redir}<br/>
<a href="add">添加用户</a>
<a href="login">用户登录</a><br/>
<!-- 获取的是map，所以需要先.value -->
<c:forEach items="${users }" var="user">
    <a href="${user.value.username}">查看用户</a><a href="${user.value.username}/update">更新</a><a href="${user.value.username}/delete">删除</a>${user.value.username }=====${user.value.nickname }<br/>
</c:forEach>
</body>
</html>
