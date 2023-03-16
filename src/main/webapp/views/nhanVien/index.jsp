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
    <h1 >Thông tin Nhân Viên</h1>
    <a href="/Assignment_Sof3011_war_exploded/nhan-vien/create" class="btn btn-success mt-3">Add</a>
    <c:if test="${ f:length(list) == 0 }">
        <h4 class="text-center">Không có dữ liệu</h4>
    </c:if>

    <c:if test="${ f:length(list) != 0 }">


        <table class="table table-bordered mt-5">
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã</th>
                <th>Họ và Tên</th>
                <th>Giới Tính</th>
                <th>Ngày Sinh</th>
                <th>SDT</th>
                <th>Địa Chỉ</th>
                <th>Email</th>
                <th>Cửa Hàng</th>
                <th>Chức Vụ</th>
                <th>Trạng Thái</th>
                <th class="col-2 text-center">Action</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="nhanVien" items="${ list }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${ nhanVien.ma }</td>
                <td>${ nhanVien.ho } ${nhanVien.tenDem} ${nhanVien.ten}</td>
                <td>${ nhanVien.gioiTinh }</td>
                <td>${ nhanVien.ngaySinh }</td>
                <td>${ nhanVien.sdt }</td>
                <td>${ nhanVien.diaChi }</td>
                <td>${ nhanVien.cuaHang }</td>
                <td>${ nhanVien.chucVu }</td>
                <td>${ nhanVien.email }</td>
                <td>${ nhanVien.trangThai == 1 ? "Đang Làm" : "Đã Nghỉ" }</td>
                    <td>
                        <a href="/Assignment_Sof3011_war_exploded/nhan-vien/edit?ma=${nhanVien.ma}" class="btn btn-primary">Edit</a>
                        <a href="/Assignment_Sof3011_war_exploded/nhan-vien/delete?ma=${nhanVien.ma}" class="btn btn-danger">Delete</a>
                    </td>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
