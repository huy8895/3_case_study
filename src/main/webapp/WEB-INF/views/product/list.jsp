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
        <c:if test="${customer != null}">
            <c:out value="customer number: ${customer.getCusNumber()}"></c:out>
        </c:if>
        <c:if test="${customer == null}">
            <c:out value="customer number: null"></c:out>
        </c:if>
    </h2>
    <h2>
        <a href="/products?action=create">Add New products</a>

        <form action="/cart?action=showCart" method="post">
            <input type="hidden" name="cusNumber" value="${customer.getCusNumber()}">
            <button type="submit">Cart</button>
        </form>
    </h2>
    <div class="container">
        <form method="get" action="/products">
            <input type="text" name="SearchBox_productName" placeholder="productName">
            <input type="text" name="SearchBox_minPrice" placeholder="minPrice">
            <input type="text" name="SearchBox_maxPrice" placeholder="maxPrice">
            <input type="text" name="SearchBox_productBrand" placeholder="productBrand">
            <input type="text" name="SearchBox_productLine" placeholder="productLine">
            <input type="hidden" name="action" value="search">
            <input type="hidden" name="cusNumber" value="${customer.getCusNumber()}">
            <input type="submit" value="search">
        </form>
        <p>results: <c:out value="${results_count}"></c:out></p>
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
                        <form action="/cart" method="post">
                            <input type="hidden" name="cusNumber" value="${customer.getCusNumber()}">
                            <input type="hidden" name="productCode" value="${product.getProductCode()}">
                            <input type="hidden" name="action" value="add">
                            <button type="submit">Add to Cart</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>