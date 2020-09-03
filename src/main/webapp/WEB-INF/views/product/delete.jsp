<%--
  Created by IntelliJ IDEA.
  User: LapTop
  Date: 9/3/2020
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<form method="post">
    <table border="1" cellpadding="5">
        <caption>
            <h2>Back To List Product</h2>
        </caption>
        <tr>
            <th>Product ID :</th>
            <th>Product Name :</th>
            <th>Product Brand :</th>
            <th>Product Price :</th>
            <th>Product Image :</th>
            <th>Product Line :</th>
        </tr>
        <tr>
            <td>${product.getProductCode()}</td>
            <td>${product.productName}</td>
            <td>${product.productBrand}</td>
            <td>${product.productPrice}</td>
            <td>${product.productImage}</td>
            <td>${product.productLine}</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Delete"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
