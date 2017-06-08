<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="com.mysql.jdbc.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="javax.swing.plaf.synth.SynthTextAreaUI" %>
<%@ page import="com.sun.xml.internal.bind.v2.TODO" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.sql.ResultSet" %><%--
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

    String[] hobbies = request.getParameterValues("hobbies");
    String[] cities = request.getParameterValues("cities");
    System.out.println("hobbies: " + Arrays.toString(hobbies));
    System.out.println("cities: " + Arrays.toString(cities));

    //TODO 验证nicck，mobil的唯一性
    new Driver();
    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");

    String sqlNick = "SELECT * FROM db_javaee.user WHERE nick = ?";
    PreparedStatement statement = connection.prepareStatement(sqlNick);
    statement.setString(1,nick);
    ResultSet resultSet = statement.executeQuery();
    boolean isNickExist = resultSet.next();
    String sqlMobile = "SELECT * FROM db_javaee.user WHERE mobile = ?";
    statement = connection.prepareStatement(sqlMobile);
    statement.setString(1, mobil);
//    resultSet = statement.executeQuery();
    boolean isMobileExist = resultSet.next();
//    statement.executeUpdate();//executeUpdate广义上的更新，包括insert，update，delete语句

    //TODO 手机号和昵称都不存在的情况
    if (isNickExist) {
        request.setAttribute("message", "昵称已经存在");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else if (isMobileExist) {
        request.setAttribute("message", "手机号已经存在");
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    } else {
        String sql = "INSERT INTO db_javaee.user VALUE (NULL ,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nick);
        statement.setString(2, mobil);
        statement.setString(3, password);
//        statement.executeUpdate();
        response.sendRedirect("index.jsp");//重定向跳转到index.jsp页面
    }
    resultSet.close();
    statement.close();
    connection.close();
%>
</body>
</html>
