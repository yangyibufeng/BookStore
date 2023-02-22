<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/2/7
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 写base标签，永远固定相对路径的跳转结果，FuckBook后面又多写了两级目录是因为要适配原来的连接中上跳两级目录，这样只需要更改base链接，不需要再动其余代码了-->
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>
<base href="<%=basePath %>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
