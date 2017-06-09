<%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/9
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录<%=session.getId()%></h1>
<form action=""method="post">
    <input type="text" placeholder="手机号">
    <input type="text" placeholder="密码">
    <input type="submit" value="登录" >
</form>
<p>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println(message);
        }
    %>
</p>
<a href="signup.jsp">注册</a>
</body>
</html>
