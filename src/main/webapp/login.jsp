<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/7
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String mobile = request.getParameter("mobil");
    String password = request.getParameter("password");
    new Driver();
    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
    String sql = "SELECT * FROM db_javaee.user WHERE mobil=? AND password=?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1,mobile);
    statement.setString(2,password);
    System.out.println(statement);
//    statement.executeUpdate();
    ResultSet resultSet = statement.executeQuery();//executeQuery()广义上的更新包括：update,insert,delete返回结果集ResultSet
    if(resultSet.next()){
        response.sendRedirect("home.jsp");// 跳转到home.jsp页面
    }

    statement.close();//关闭资源
    connection.close();

%>
</body>
</html>
