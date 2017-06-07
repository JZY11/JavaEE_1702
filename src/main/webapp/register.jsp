<%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/7
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<%
    String nick = request.getParameter("nick");
    out.print(nick);
    String mobil = request.getParameter("mobil");
    out.print(mobil);
    String password = request.getParameter("password");
    out.print(password);
%>
</body>
</html>
