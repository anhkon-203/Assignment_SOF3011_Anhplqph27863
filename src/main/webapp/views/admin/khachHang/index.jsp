<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/9/2023
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
    <h1 >Thông tin khách hàng</h1>
    <a href="/Assignment_Sof3011_war_exploded/khach-hang/create" class="btn btn-success mt-3">Add</a>
    <c:if test="${ f:length(listKhachHang) == 0 }">
        <h4 class="text-center">Không có dữ liệu</h4>
    </c:if>

    <c:if test="${ f:length(listKhachHang) != 0 }">


        <table class="table table-bordered mt-5" >
            <tr>
                <th>STT</th>
                <th>Mã khách hàng</th>
                <th>Họ và tên</th>
                <th>Ngày sinh</th>
                <th>Địa chỉ</th>
                <th>Thành phố</th>
                <th>Quốc gia</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach var="khachHang" items="${listKhachHang}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${khachHang.ma}</td>
                    <td>${khachHang.ho} ${khachHang.tenDem} ${khachHang.ten}</td>
                    <td>${khachHang.ngaySinh}</td>
                    <td>${khachHang.diaChi}</td>
                    <td>${khachHang.thanhPho}</td>
                    <td>${khachHang.quocGia}</td>
                    <td>${khachHang.sdt}</td>
                    <td>${khachHang.email}</td>
                    <td>
                        <a href="/Assignment_Sof3011_war_exploded/khach-hang/edit?ma=${khachHang.ma}" class="btn btn-primary">Edit</a>
                        <a href="/Assignment_Sof3011_war_exploded/khach-hang/delete?ma=${khachHang.ma}" class="btn btn-danger">Delete</a>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
