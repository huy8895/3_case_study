<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: huy8895
  Date: 9/3/20
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cart</title>
</head>

<div class="container" align="center">
    <h1>cart</h1>
    <h2>
        <a href="/products?action=create">Add New products</a><br>
        <form action="/products"  method="post">
            <input type="hidden" name="cusNumber" value="${customer.getCusNumber()}">
            <input type="hidden" name="action" value="">
            <button type="submit">back to list</button>
        </form>
        <c:if test="${customer != null}">
            <c:out value="customer number: ${customer.getCusNumber()}"></c:out>
        </c:if>
        <c:if test="${customer == null}">
            <c:out value="customer number: null"></c:out>
        </c:if>
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
                <th>Quantity</th>
            </tr>
            <c:forEach var="cart" items="${cartList}">
                <tr>
                    <c:set var = "product" scope = "session" value = "${productDAO.selectProduct(cart.getProductCode())}"/>
                    <c:set var = "quantity" scope = "session" value = "${cart.getQuantity()}"/>

                    <td><c:out value="${product.productName}"/></td>
                    <td><c:out value="${product.productBrand}"/></td>
                    <td><c:out value="${product.productPrice}"/></td>
                    <td><img src="${product.productImage}" alt="img" height="50px" width="auto"></td>
                    <td><c:out value="${quantity}"/></td>
                    <td>
                        <a href="/cart?action=edit&id=${product.productCode}">Edit</a>
                        <a href="/cart?action=delete&id=${product.productCode}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
</html>
