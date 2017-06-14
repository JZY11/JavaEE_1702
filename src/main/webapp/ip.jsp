<%@ page import="demo.servlet.IpAction" %><%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/14
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="ip()">
<form id = "form"action="ip" method="post">
    <input type="text" name = "ip">
    <input type="submit" value="查询">
</form>
${sessionScope.geo}
<%
    if (session.getAttribute("geo") == null) {
        String ip = request.getRemoteAddr();
        out.print(ip + "<br>");
        out.print(IpAction.getGeo(ip));
    }
%>
</body>
</html>
