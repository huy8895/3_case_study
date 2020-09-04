<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/3/20
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change pass word</title>
</head>
<body>
<form method="post" action="/login?action=changepassword">
    ten dang nhap<br>
    <input type="text" name="username"><br>
    mat khau hien tai<br>
    <input type="password" name="password"><br>
    mat khau moi<br>
    <input type="password" name="newpassword"><br>
    <button type="submit" >submit</button>
    <button type="reset" >reset</button>

</form>
</body>
</html>
