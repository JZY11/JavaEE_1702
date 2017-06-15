<%@ page import="demo.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/7
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th,
        td {
            border: 1px solid #333;
        }
    </style>
    <script>
        function del() {
            return confirm('是否确定要删除');
        }
        function selectAll() {
            var cb = document.getElementById('cb');
            var items = document.getElementsByClassName('to_be_delete');
            for(var i = 0; i <items.length;i++){
                items[i].checked = cb.checked
            }
        }
    </script>
</head>
<body>

<c:if test="${sessionScope.nick eq null}">
    <c:redirect url="default.jsp"/>
</c:if>

${sessionScope.nick}

<%--<%--%>
    <%--if (session.getAttribute("nick") == null) {// 解决权限问题--%>
        <%--response.sendRedirect("default.jsp");--%>
    <%--}--%>
<%--%>--%>
<h1>主页<%=session.getId()%>
</h1>
<%--<p><%=session.getAttribute("nick")%></p>--%>
<%--<a href="second.jsp">第二页</a>--%>
<%--<%--%>
<%--pageContext.setAttribute("key", "value");--%>
<%--application.setAttribute("app-key", "app-value");--%>
<%--%>--%>
<%--<%=pageContext.getAttribute("key")%>--%>
<%--<%=application.getAttribute("app-key")%>--%>
<p><a href="user?action=logout">注销</a></p>
<hr>
<form action="student" method="post" style="text-align: center">
    <%--<div style="width: 100%;text-align: center">--%>
    <input type="hidden" name="action" value="add">
    <input type="text" name="name" value="姓名"><br>
    <input type="text" name="gender" value="性别"><br>
    <input type="date" name="dob" value="出生日期"><br>
    <input type="submit" value="添加">
    <%--</div>--%>
</form>
<hr>
<%--<div style="width: 100%;text-align: center">--%>
<form action="student" method="post">
    <input type="hidden" name="action" value="batchRemove">
<table border="1">

    <c:choose>
        <c:when test="${fn:length(sessionScope.students) eq 0}">
            当前没有记录
        </c:when>
        <c:otherwise>
            <tr>
                <th><input id="cb" type="checkbox" onclick="selectAll()">序号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>出生日期</th>
                <th colspan="2">操作</th>
            </tr>
        </c:otherwise>
    </c:choose>

    <c:forEach var="student" items="${sessionScope.students}" varStatus="vs">
        <tr>
            <td><input class="to_be_delete" type="checkbox" name="ids" value="${student.id}">${vs.count}</td>
            <td>${student.name}</td>
            <td>${student.gender}</td>
            <td>${student.dob}</td>
            <td><a href="student?action=queryById&id=${student.id}">编辑</a></td>
            <td><a href="student?action=remove&id=${student.id}" onclick="return del()">删除</a></td>
        </tr>
    </c:forEach>


    <%--<%--%>

    <%--List<Student> resultSet = (List<Student>) session.getAttribute("students");--%>
    <%--for (Student student : resultSet) {--%>
    <%--out.print("<tr>" + "<td>" + student.getId() + "</td>" + "<td>" + student.getName() + "</td>" + "<td>" + student.getGender() + "</td>" + "<td>" + student.getDob() + "<td><a href='student?action=queryById&id=" + student.getId() + "'>编辑</a></td>" + "<td><a href='student?action=remove&id=" + student.getId() + "' onclick='return del()'>删除</a></td>" + "</tr>");--%>
    <%--}--%>

    <%--%>--%>
</table>
        <input type="submit" value="删除">

</form>
<%--</div>--%>
<hr>

${requestScope.message}

<%--<%--%>
<%--String message = (String) request.getAttribute("message");--%>
<%--if (message != null) {--%>
<%--out.print(message);--%>
<%--}--%>
<%--%>--%>
</body>
</html>
