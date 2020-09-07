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
    <title>list products</title>
</head>
<body>
<div class="container" align="center">
    <h1>Product Management</h1>
    <h2>
        <a href="/products?action=create">Add New Product</a>
    </h2>
    <div class="container">
        <form method="get" action="/products">
            <input type="text" name="Search">
            <input type="submit" value="Search">
        </form>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Products</h2></caption>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Brand</th>
                <th>Product Price</th>
                <th>Product Image</th>
                <th>Product Line</th>
                <th>Actions</th>

            </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.getProductCode()}</td>
                    <td>${product.productName}</td>
                    <td>${product.productBrand}</td>
                    <td>${product.productPrice}</td>
                    <td>${product.productImage}</td>
                    <td>${product.productLine}</td>
                    <td>
                        <a href="/products?action=edit&id=${product.productCode}">Edit</a>
                        <a href="/products?action=delete&id=${product.productCode}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>