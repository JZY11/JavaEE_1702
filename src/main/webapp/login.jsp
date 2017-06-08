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
<pre>
     request:
        getRemoteAddr()
        getParameter()
        getRequestDispatcher().forward()
        setAttribute()
        getAttribute()
    response:
        sendRedirect()
</pre>
<body>
<%
    String mobile = request.getParameter("mobil");//从表单中获取添加的值即获取表单参数
    String password = request.getParameter("password");
    out.println(mobile + "," + password);

    new Driver();//进行数据库驱动
    java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
    String sql = "SELECT * FROM db_javaee.user WHERE mobil=? AND password=?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1,mobile);
    statement.setString(2,password);
    System.out.println(statement);
//    statement.executeUpdate();
    ResultSet resultSet = statement.executeQuery();//executeQuery()查询    返回一结果集ResultSet


    if(resultSet.next()){//从查询的结果集中判断是否有下一个(即是否含有select语句返回的所有行中的一行)
        //success  希望用户登录成功后能显示用户的昵称
        System.out.println(resultSet.getString("nick"));
//        request.setAttribute("nick", resultSet.getString("nick"));
        session.setAttribute("nick",resultSet.getString("nick"));
//        response.sendRedirect("home.jsp");//Redirect：重定向  不能保存request范围内的属性
        request.getRequestDispatcher("home.jsp").forward(request,response);// 转发到home.jsp页面  能保存request内的属性
    }else {
        //failed
//        response.sendRedirect("index.jsp"); // redirect 重定向 地址栏地址有变化   能保存request内的属性
        request.setAttribute("message","用户名或密码错误");
        request.getRequestDispatcher("index.jsp").forward(request,response);// forward 转发 地址栏地址没有变化
    }

    resultSet.close();
    statement.close();
    connection.close();//释放资源

%>
</body>
</html>
