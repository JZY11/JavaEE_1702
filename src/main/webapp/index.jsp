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
<h1>登录<%=session.getId()%></h1><!-- sessionId,session开始的地方，网站的第一个jsp动态页面 -->
<form action="login.jsp" method="post"><!--method 默认的为get,改为post更保密，在地址栏里不会看到自己的电话和密码了-->
  <input type="text" name="mobil" placeholder="手机号"><br><!-- placeholder占位栏-->
  <input type="password" name="password" placeholder="密码"><br>
  <input type="submit" value="登录">
</form>
<%--<p><%=request.getAttribute("message")%></p>--%>


<%--<%=(request.getAttribute("message") != null) ? request.getAttribute("message") : ""%>--%>
<p>
    <%
        String message = (String) request.getAttribute("message");//取得message这一消息
        if (message != null) {//解决登录页面有null的问题，做以判断就好
            out.println(message);
        }
    %>
    <%-- 判断属性是否有对应的值 --%>
</p>
<a href="signup.jsp">注册</a>
</body>
</html>