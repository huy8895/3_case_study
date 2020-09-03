<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/2/20
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Customer Confirm</title>
</head>
<body>
<form method="post">
    <table border="1" cellpadding="5">
        <caption><h2>List of Customers</h2></caption>
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Full Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Email</th>

        </tr>
            <tr>
                <td><c:out value="${customer.getCusNumber()}"/></td>
                <td><c:out value="${customer.userName}"/></td>
                <td><c:out value="${customer.cusName}"/></td>
                <td><c:out value="${customer.cusPhoneNumber}"/></td>
                <td><c:out value="${customer.cusAddress}"/></td>
                <td><c:out value="${customer.cusEmail}"/></td>
            </tr>
        <tr>
            <td>
                <input type="submit" value="Delete">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
