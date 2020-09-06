<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/3/20
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Email:</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
        </div>
        <input type="hidden" name="action" value="login">
        <button type="submit" class="btn btn-primary">Login</button>
        <a type="button" class="btn btn-primary" href="/customers?action=create" >Create new</a>
        <a type="button" class="btn btn-primary" href="/login?action=changepassword" >Change password</a>
        <c:out value="${message}"></c:out>
    </form>
</div>
</body>
</html>
