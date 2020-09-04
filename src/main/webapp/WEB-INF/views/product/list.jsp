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
        <a href="/products?action=create">Add New products</a>
        <a href="/cart?action=showCart">Cart</a>
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
                <th>Product Name</th>
                <th>Brand</th>
                <th>price</th>
                <th>image</th>
                <th>detail</th>

            </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.productBrand}"/></td>
                    <td><c:out value="${product.productPrice}"/></td>
                    <td><img src="${product.productImage}" alt="img" height="50px" width="auto"></td>
                    <td><c:out value="${product.productLine}"/></td>
                    <td>
                        <a href="/products?action=edit&id=${product.getProductCode()}">Edit</a>
                        <a href="/products?action=delete&id=${product.getProductCode()}">Delete</a>
                    </td>
                    <td>
                        <form method="post" action="/cart">
<%--                            <a href="/cart?action=showcart&id=${customer.getCusNumber()}">add to cart</a>--%>

                            <button type="submit" value="add" name="action" id=${product.getProductCode()}>add</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>