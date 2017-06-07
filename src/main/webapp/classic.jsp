<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!--这是一个非常典型的JSP，它包含了JSP中常用的元素-->
<%!
    String getDate() {
        return new java.util.Date().toString();
    }
    int count = 10;
%>
<html>
<head><title>一个典型的JSP</title></head>
<body>
<%--<jsp:include page="header.jsp"/>--%>
<%@include file="header.jsp"%>
<div style="text-align: center">
    <table style="margin: 0 auto;">
        <tr style="background: red;">
            <th>--你-----好-------啊--</th>
        </tr>
        <%
            // color
            String c1 = "yellow", c2 = "blue";
            for (int i = 0; i < count; i++) {
                String color;
                if (i % 2 == 0) {
                    color = c1;
                } else {
                    color = c2;
                }
                out.println("<tr style=\"background:" + color + ";\"><td>-</td></tr>");
            }
        %>
    </table>
    //        out 输出
    //        request 请求\ [rɪ'kwest]
    //        response 响应\ [rɪ'spɒns]
    //        session 会话\ ['seʃ\(ə\)n]
    //        application 应用程序\ [ˌæplɪ'keɪʃ\(ə\)n]
    //        pageContext 上下文\ ['kɒntekst]
    //        page
    //        config 配置\ [kən'fɪɡ]
    //        exception
    <hr/>
    当前的时间是：
    <%-- 下面是使用表达式的例子 --%>
    <%=getDate()%>
</div>
</body>
</html>