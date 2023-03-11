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
    <h1 >Thông tin khách hàng</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Mã khách hàng</th>
            <th>Họ</th>
            <th>Tên đệm</th>
            <th>Tên</th>
            <th>Ngày sinh</th>
            <th>Sdt</th>
            <th>Địa chỉ</th>
            <th>Mật khẩu</th>
            <th>Quốc gia</th>
            <th>Thành phố</th>
            <th scope="col">
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">${qlkh.ma}</th>
            <td>${qlkh.ho}</td>
            <td>${qlkh.ten_dem}</td>
            <td>${qlkh.ten}</td>
            <td>${qlkh.ngay_sinh}</td>
            <td>${qlkh.sdt}</td>
            <td>${qlkh.dia_chi}</td>
            <td>${qlkh.mat_khau}</td>
            <td>${qlkh.quoc_gia}</td>
            <td>${qlkh.thanh_pho}</td>
        </tr>
        </tbody>
    </table>

</body>
</html>
