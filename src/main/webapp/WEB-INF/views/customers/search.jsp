<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LapTop
  Date: 9/3/2020
  Time: 9:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<center>
    <h1>Search Result</h1>
    <a href="/customers">Back To List Customer</a>

</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption>
            <h2>List Of Customers</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Full Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${customerList}" var="customer">
            <td>${customer.cusNumber}</td>
            <td>${customer.userName()}</td>
            <td>${customer.cusName}</td>
            <td>${customer.cusPhoneNumber}</td>
            <td>${customer.cusAddress}</td>
            <td>${customer.cusEmail}</td>
            <td>
                <a href="/customers?action=edit&id=${customer.cusNumber}">Edit</a>
                <a href="/customers?action=delete&id=${customer.cusNumber}">Delete</a>
            </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
