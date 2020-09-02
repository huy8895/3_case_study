<%--
  Created by IntelliJ IDEA.
  User: LapTop
  Date: 9/1/2020
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list products</title>
</head>
<body>
<div class="container" align="center">
    <h1>Product Management</h1>
    <h2>
        <a href="/products?action=create">Add New products</a>
    </h2>
    <div class="container">
        <form method="get" action="/products">
            <input type="text" name="SearchBox">
            <input type="submit" name="action" value="Search">
        </form>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of products</h2></caption>
            <tr>
                <th>ID</th>
                <th>userName</th>
                <th>Full Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Email</th>

            </tr>
            <c:forEach var="products" items="${customerList}">
                <tr>
                    <td><c:out value="${customer.getCusNumber()}"/></td>
                    <td><c:out value="${customer.userName}"/></td>
                    <td><c:out value="${customer.cusName}"/></td>
                    <td><c:out value="${customer.cusPhoneNumber}"/></td>
                    <td><c:out value="${customer.cusAddress}"/></td>
                    <td><c:out value="${customer.cusEmail}"/></td>
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