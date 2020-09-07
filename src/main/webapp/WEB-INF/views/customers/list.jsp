<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/1/20
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list customers</title>
</head>
<body>
<div class="container" align="center">
    <h1>Customer Management</h1>
    <h2>
        <a href="/customers?action=create">Add New Customer</a>
    </h2>
    <div class="container">
        <form method="get" action="/customers">
            <input type="text" name="SearchName">
            <input type="submit" name="action" value="Search">
        </form>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Customers</h2></caption>
            <tr>
                <th>ID</th>
                <th>UserName</th>
                <th>Full Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Email</th>
                <th>Actions</th>

            </tr>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td>${customer.getCusNumber()}</td>
                    <td>${customer.userName}</td>
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
</div>
</body>

</html>
