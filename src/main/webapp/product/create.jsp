<%--
  Created by IntelliJ IDEA.
  User: LapTop
  Date: 9/1/2020
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="products?action=products">List All Product </a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>Add new product</caption>
            <tr>
                <th>Name :</th>
                <td>
                    <input type="text" name="name" id="name" size="45">
                </td>
            </tr>
            <tr>
                <th>Price :</th>
                <td>
                    <input type="text" name="price" id="price" size="45">
                </td>
            </tr>
            <tr>
                <th>Description :</th>
                <td>
                    <input type="text" name="description" id="description" size="45">
                </td>
            </tr>
            <tr>
                <th>Image :</th>
                <td>
                    <input type="text" name="image" id="image" size="45">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</div>



</body>
</html>
