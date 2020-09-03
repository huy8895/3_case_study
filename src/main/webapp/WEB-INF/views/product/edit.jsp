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
    <title>Product Management Application</title>
</head>
<body>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit Product</h2>
            </caption>
            <input type="hidden" name="id" value="${requestScope["product"].getProductCode()}"/>
            <a href="/products">Back To List</a>
            <tr>
                <th>Product Name :</th>
                <td>
                    <input type="text" name="productName" id="productName"
                    value="${requestScope["product"].getProductName()}"/>
                </td>
            </tr>
            <tr>
                <th>Product Brand :</th>
                <td>
                    <input type="text" name="productBrand" id="productBrand"
                           value="${requestScope["product"].getProductBrand()}"/>
                </td>
            </tr>
            <tr>
                <th>Product Price :</th>
                <td>
                    <input type="text" name="productPrice" id="productPrice"
                           value="${requestScope["product"].getProductPrice()}"/>
                </td>
            </tr>
            <tr>
                <th>Product Image :</th>
                <td>
                    <input type="text" name="productImage" id="productImage"
                           value="${requestScope["product"].getProductImage()}"/>
                </td>
            </tr>
            <tr>
                <th>Product Line :</th>
                <td>
                    <input type="text" name="productLine" id="productLine"
                           value="${requestScope["product"].getProductLine()}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
