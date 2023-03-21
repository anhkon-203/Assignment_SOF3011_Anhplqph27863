<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/21/2023
  Time: 7:01 PM
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
<c:if test="${ f:length(sanPham) == 0 }">
    <h4 class="text-center">Không có dữ liệu</h4>
</c:if>

<c:if test="${ f:length(sanPham) != 0 }">

    <table class="table table-bordered mt-5">
        <thead>
        <tr>
            <th>STT</th>
            <th>Sản phẩm</th>
            <th>NSX</th>
            <th>Màu sắc</th>
            <th>Dòng Sản phẩm</th>
            <th>Năm bảo hành</th>
            <th>Số lượng</th>
            <th>Giá bán</th>
            <th>Mô tả </th>
            <th class="col-2 text-center">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="ctsp" items="${ sanPham }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${ ctsp.tenSp }</td>
                <td>${ ctsp.tenNSX }</td>
                <td>${ ctsp.tenMauSac }</td>
                <td>${ ctsp.tenDongSp }</td>
                <td>${ ctsp.namBaoHanh }</td>
                <td>${ ctsp.soLuongTon }</td>
                <td>${ ctsp.giaBan }</td>
                <td>${ ctsp.moTa }</td>
                <c:if test="${user != null}">
                    <td class="text-center">
                        <a href="/Assignment_Sof3011_war_exploded/chi-tiet-san-pham/edit?id=${ ctsp.id }"
                           class="btn btn-primary">Thêm vào giỏ hàng</a>
                        <a  href="/Assignment_Sof3011_war_exploded/chi-tiet-san-pham/edit?id=${ ctsp.id }"
                            class="btn btn-success">Xem chi tiết</a>
                    </td>
                </c:if>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
