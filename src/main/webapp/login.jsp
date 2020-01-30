<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="/shiro/login.do" method="post">
        username:<input type="text" name="username"><br><br>
        password:<input type="password" name="password"><br><br>
        <input type="submit" value="sumbit">
    </form>
</body>
</html>
