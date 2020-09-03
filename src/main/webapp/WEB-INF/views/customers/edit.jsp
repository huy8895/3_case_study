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
</head>
<body>
<center>
    <h1>Customer Management</h1>
    <h2>
        <a href="customers?action=customers">List All Customer</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Edit Customers </h2>
            </caption>
            <input type="hidden" name="id" value="${requestScope["customer"].getCusNumber()}"/>
            <a href="/customers">Back To List Customer</a>
            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="userName" id="userName"
                           value="${requestScope["customer"].getUserName()}" size="45" disabled/>
                </td>
            </tr>
            <tr>
                <th>Full Name:</th>
                <td>
                    <input type="text" name="cusName" id="cusName"
                           value="${requestScope["customer"].getCusName()}" size="45"/>
                </td>
            </tr>
            <tr>
                <th>PhoneNumber:</th>
                <td>
                    <input type="text" name="cusPhoneNumber" id="cusPhoneNumber"
                           value="${requestScope["customer"].getCusPhoneNumber()}"size="45"/>
                </td>
            </tr>
            <tr>
                <th>Address:</th>
                <td>
                    <input type="text" name="cusAddress" id="cusAddress"
                           value="${requestScope["customer"].getCusAddress()}" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>
                    <input type="text" name="cusEmail" id="cusEmail"
                           value="${requestScope["customer"].getCusEmail()}"size="45"/>
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
