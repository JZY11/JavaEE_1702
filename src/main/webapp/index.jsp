<%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/6
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>index page</title>
</head>
<body>
<h1>登录<%=session.getId()%></h1>
<form action="login.jsp">
  <input type="text" name="mobil" placeholder="手机号"><br>
  <input type="password" name="password" placeholder="密码"><br>
  <input type="submit" value="登录">
</form>
<%--<p><%=request.getAttribute("message")%></p>--%>
<p>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println(message);
        }
    %>
    <%-- 判断属性是否有对应的值 --%>
</p>
<a href="signup.jsp">注册</a>
</body>
</html>