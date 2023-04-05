<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 4/5/2023
  Time: 5:43 PM
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
<h2 class="mt-3">Quản Lý Hóa Đơn</h2>

<c:if test="${ f:length(listHoaDon) == 0 }">
    <h4 class="text-center">Không có dữ liệu</h4>
</c:if>

<c:if test="${ f:length(listHoaDon) != 0 }">

    <table class="table table-bordered mt-5">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên Khách Hàng</th>
            <th>SDT Khách Hàng</th>
            <th>Địa Chỉ Khách Hàng</th>
            <th>Ngày Tạo</th>
            <th>Ngày Ship</th>
            <th>Ngày Nhận</th>
            <th>Ngày Thanh Toán</th>
            <th class="col-2">Trạng Thái</th>
            <th class="col-2 text-center">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hd" items="${ listHoaDon }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${ hd.ma }</td>
                <td>${ hd.tenNguoiNhan }</td>
                <td>${ hd.sdt }</td>
                <td>${ hd.diaChi }</td>
                <td>${ hd.ngayTao }</td>
                <td>${ hd.ngayShip}</td>
                <td>${ hd.ngayNhan }</td>
                <td>${ hd.ngayThanhToan }</td>
                <td>${ hd.tinhTrang}</td>
                <td>
                    <c:if test="${hd.tinhTrang eq 'Chờ giao hàng'}">
                        <div class="col-md-1 col-2 ms-4">
                            <form action="/Assignment_Sof3011_war_exploded/hoa-don/update?maHD=${hd.ma}"
                                  method="post">
                                <input type="hidden" name="maHD" value="${ma.maHD}">
                                <button type="submit" class="btn btn-success ">
                                    Giao hàng
                                </button>
                            </form>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
