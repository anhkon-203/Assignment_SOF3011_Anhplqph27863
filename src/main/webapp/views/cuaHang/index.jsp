<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/9/2023
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
    <h1 >Thông tin Cửa hàng</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Mã Cửa hàng</th>
            <th>Tên</th>
            <th>Địa chỉ</th>
            <th>Quốc gia</th>
            <th>Thành phố</th>
            <th scope="col">
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">${qlch.ma}</th>
            <td>${qlch.ten}</td>
            <td>${qlch.dia_chi}</td>
            <td>${qlch.quoc_gia}</td>
            <td>${qlch.thanh_pho}</td>
        </tr>
        </tbody>
    </table>

</body>
</html>
