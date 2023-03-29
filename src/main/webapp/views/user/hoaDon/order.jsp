<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/28/2023
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="xxx"
          crossorigin="anonymous"/>
</head>
<style>
    .container {
        max-width: 1000px;
    }

    body {
        background-color: #f8f9fa;
    }
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1 class="mt-5">Cảm ơn bạn đã đặt hàng!</h1>
            <p class="lead">Hoá đơn của bạn đã được ghi nhận và đang được xử lý.</p>
            <hr>
            <p class="lead">Thông tin hoá đơn:</p>
            <table class="table">
                <tr>
                    <td>Tên khách hàng:</td>
                    <td>${hoaDon.khachHang.tenKhachHang}</td>
                </tr>
                <tr>
                    <td>Địa chỉ:</td>
                    <td>${hoaDon.khachHang.diaChi}</td>
                </tr>
                <tr>
                    <td>Số điện thoại:</td>
                    <td>${hoaDon.khachHang.soDienThoai}</td>
                </tr>
                <tr>
                    <td>Ngày tạo:</td>
                    <td>${hoaDon.ngayTao}</td>
                </tr>
                <tr>
                    <td>Trạng thái:</td>
                    <td>${hoaDon.trangThai == 0 ? 'Chưa xử lý' : 'Đã xử lý'}</td>
                </tr>
            </table>
            <p class="lead">Thông tin liên hệ:</p>
            <p class="lead">Nếu bạn có bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi:</p>
            <ul class="list-unstyled">
                <li><strong>Địa chỉ:</strong> 123 Đường ABC, Quận XYZ, Thành phố HCM</li>
                <li><strong>Điện thoại:</strong> 0123456789</li>
                <li><strong>Email:</strong> info@example.com</li>
            </ul>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary">Quay lại trang chủ</a>
        </div>
    </div>
</div>

<script src="/Assignment_Sof3011_war_exploded/js/bootstrap.min.js"></script>
</body>
</html>
