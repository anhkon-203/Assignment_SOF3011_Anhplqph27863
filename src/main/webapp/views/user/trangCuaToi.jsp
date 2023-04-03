<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 4/3/2023
  Time: 7:25 PM
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
<div class="container">
    <div class="row">
        <div class="col-12">
            <c:forEach var="ghct" items="${listHoaDonChiTietViewModel}" varStatus="status">
                <div class="card mb-2">
                    <div class="card-header">
                        <span id="deal">Deal Sốc</span>
                        <a href="#" class="text-decoration-none text-danger">Thêm</a>
                    </div>
                    <div class="card-body">
                        <div class="row align-items-center">
                            <span class="fw-bold">${ghct.tenSP}</span>
                            <div class="col-1">
                                <img src="${ghct.srcImage}" alt="ảnh sản phẩm" class="img-fluid d-flex">
                            </div>
                            <div class="col-md-3 col-7">
                                <img src="/Assignment_Sof3011_war_exploded/img/freeShip.png" class="img-fluid"
                                     alt="Miễn phí vận chuyển">
                                <img src="/Assignment_Sof3011_war_exploded/img/7.png" class="img-fluid "
                                     alt="Miễn phí vận chuyển">
                                <span id="textProduct">7 ngày miễn phí trả hàng</span>
                            </div>
                            <div class="col-md-1 col-2">
                                <span id="priceProduct" class="text-center">${ghct.donGia}</span>
                            </div>
                            <div class="col-md-2 col-3">
                                <div class="input-group">
                                    <label>Số lượng</label>
                                    <input type="text" class="form-control" id="quantity-input" value="${ghct.soLuong}">
                                </div>
                            </div>
                            <div class="col-md-1 col-2">
                                <span class="text-center text-truncate text-danger">${ghct.donGia * ghct.soLuong}</span>
                            </div>
                            <div class="col-md-1 col-2">
                                    <%--                                <form action="/Assignment_Sof3011_war_exploded/GioHangUserServlet/delete" method="GET">--%>
                                    <%--                                    <--%>
                                    <%--                                </form>--%>
                                <label>Tình trạng</label>
                                <span class="text-center text-truncate text-danger">${ghct.tinhTrang == 1 ? "Đã giao hàng" : "Chưa giao hàng"}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
