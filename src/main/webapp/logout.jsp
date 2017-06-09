<%
    session.invalidate();
    response.sendRedirect("index.jsp");//重定向跳转到index.jsp页面
%>