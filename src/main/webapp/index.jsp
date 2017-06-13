<%@ page import="demo.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/7
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function del() {
            return confirm('是否确定要删除');
        }
    </script>
</head>
<body>
<%
    if (session.getAttribute("nick") == null) {// 解决权限问题
        response.sendRedirect("default.jsp");
    }
%>
<h1>主页<%=session.getId()%>
</h1>
<%--<p><%=session.getAttribute("nick")%></p>--%>
<%--<a href="second.jsp">第二页</a>--%>
<%
    pageContext.setAttribute("key", "value");
    application.setAttribute("app-key", "app-value");
%>
<%--<%=pageContext.getAttribute("key")%>--%>
<%--<%=application.getAttribute("app-key")%>--%>
<p><a href="user?action=logout">注销</a></p>
<hr>
<form action="student" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" value="姓名"><br>
    <input type="text" name="gender" value="性别"><br>
    <input type="date" name="dob" value="出生日期"><br>
    <input type="submit" value="添加">
</form>
<hr>
<table border="1">
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th colspan="2">操作</th>
    </tr>
    <%
        List<Student> resultSet = (List<Student>) session.getAttribute("students");
        for (Student student : resultSet) {
            out.print("<tr>" + "<td>" + student.getId() + "</td>" + "<td>" + student.getName() + "</td>" + "<td>" + student.getGender() + "</td>" + "<td>" + student.getDob() + "<td><a href='student?action=queryById&id=" + student.getId() + "'>编辑</a></td>" + "<td><a href='student?action=remove&id=" + student.getId() + "' onclick='return del()'>删除</a></td>" + "</tr>");
        }
    %>
</table>
<hr>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.print(message);
    }
%>
</body>
</html>
