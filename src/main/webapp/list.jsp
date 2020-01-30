<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <h4>List Page</h4>

    <%--shiro标签演示--%>
    welcome:<shiro:principal/>

    <shiro:hasRole name="admin">
    <br><br>
    <a href="admin.jsp">Admin Page</a>
    </shiro:hasRole>

    <shiro:hasRole name="user">
    <br><br>
    <a href="user.jsp">User Page</a>
    </shiro:hasRole>

    <br><br>
    <a href="/shiro/testShiroAnnotation.do">testShiroAnnotation</a>

    <br><br>
    <a href="/shiro/logout.do">logout</a>

</body>
</html>
