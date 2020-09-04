<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LapTop
<<<<<<< HEAD:src/main/webapp/WEB-INF/views/products/list.jsp
  Date: 9/3/2020
  Time: 9:15 AM
=======
  Date: 9/1/2020
  Time: 2:17 PM
>>>>>>> bc5629963afa77197a8a8d1277ba5b0200f39e90:src/main/webapp/WEB-INF/views/product/search.jsp
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
    <a href="/products">Back To List Product</a>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption>
            <h2>List Of Products</h2>
        </caption>
        <tr>
            <th>Product Code</th>
            <th>Product Name</th>
            <th>Product Brand</th>
            <th>Product Price</th>
            <th>Product Image</th>
            <th>Product Line</th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <td>${product.productCode}</td>
            <td>${product.productName}</td>
            <td>${product.productBrand}</td>
            <td>${product.productPrice}</td>
            <td>${product.productImage}</td>
            <td>${product.productLine}</td>
            <td>
                <a href="/products?action=edit&id=${product.productCode}">Edit</a>
                <a href="/products?action=delete&id=${product.productCode}">Delete</a>
            </td>
        </c:forEach>
    </table>
</div>

</body>
</html>
