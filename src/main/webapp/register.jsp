<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="com.mysql.jdbc.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="javax.swing.plaf.synth.SynthTextAreaUI" %><%--
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

    new Driver();
    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
    String sql = "INSERT INTO db_javaee.user VALUE(NULL,?,?,?)";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1,nick);
    statement.setString(2,mobil);
    statement.setString(3, password);
    System.out.println(statement);
    statement.executeUpdate();

    statement.close();
    connection.close();

    response.sendRedirect("index.jsp");
%>
</body>
</html>
