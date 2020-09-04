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
    <title>Create New Product</title>
</head>
<body>
<center>
    <h1>Create New Product</h1>
    <h2>
        <a href="/products">Back To List Product</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New Product</h2>
            </caption>
            <tr>
                <th>Product Name :</th>
                <td>
                    <input type="text" name="productName" id="productName" size="55"/>
                </td>
            </tr>
            <tr>
                <th>Product Brand :</th>
                <td>
                    <input type="text" name="productBrand" id="productBrand" size="55"/>
                </td>
            </tr>
            <tr>
                <th>Product Price :</th>
                <td>
                    <input type="text" name="productPrice" id="productPrice" size="55"/>
                </td>
            </tr>
            <tr>
                <th>Product Image :</th>
                <td>
                    <input type="text" name="productImage" id="productImage" size="55"/>
                </td>
            </tr>
            <tr>
                <th>Product Line :</th>
                <td>
                    <input type="text" name="productLine" id="productLine" size="55"/>
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
