<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container" align="center">
    <h1>Product Management</h1>
    <h2>
        <a href="products?action=create">Add New Product</a>
    </h2>
    <div class="container">
        <form method="get" action="/products">
            <input type="text" name="SearchName">
            <input type="submit" name="action" value="Search">
        </form>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
                <h2>List of product</h2>
            </caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${listProduct}" var="product">
                <tr>
                <td><c:out value="${product.id}"></c:out></td>
                <td><c:out value="${product.name}"></c:out></td>
                <td><c:out value="${product.price}"></c:out></td>
                <td><c:out value="${product.description}"></c:out></td>
                <td><c:out value="${product.image}"></c:out></td>
                    <td>
                        <a href="/products?action=edit&id=${product.id}">Edit</a>
                        <a href="/products?action=delete&id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
