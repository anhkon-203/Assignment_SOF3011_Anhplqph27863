<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/11/2023
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
<h1 >Thông tin Màu sắc</h1>
<table class="table">
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th scope="col">
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">${qlms.ma}</th>
        <td>${qlms.ten}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
