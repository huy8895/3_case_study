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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption><h2>Delete Customer Confirm</h2></caption>
            <c:out value="${status}"></c:out>
            <c:if test="${customer != null}">
                <tr>
                    <th>ID</th>
                    <th>userName</th>
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
            </c:if>

        </table>
        <button type="submit">delete</button>
    </form>
</div>
</body>
</html>
