<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form method="post" action="/login?action=changepassword" >
        <div class="form-group">
            ten dang nhap<br>
            <input type="text" class="form-control" placeholder="Enter username" name="username" required><br>
            mat khau hien tai<br>
            <input type="password" class="form-control" placeholder="Enter password" name="password" required><br>
            mat khau moi<br>
            <input type="password" class="form-control" placeholder="Enter newpassword" name="newpassword" required><br>
            <button type="submit" class="btn btn-primary">submit</button>
            <button type="reset" class="btn btn-primary">reset</button>
            <a type="button" class="btn btn-primary" href="/login">Login</a>
            <p><c:out value="status: ${status}"></c:out></p>
        </div>
    </form>
</div>

</body>
</html>
