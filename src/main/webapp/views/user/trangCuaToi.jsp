<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 4/3/2023
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
    <div class="row">
        <div class="col-12">
            <c:forEach var="ghct" items="${listHoaDonChiTietViewModel}" varStatus="status">
                <div class="card mb-2">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <span id="deal">Deal Sốc</span>
                        <input type="hidden" name="maHD" value="${ghct.maHD}">
                        <a href="/Assignment_Sof3011_war_exploded/TrangCuaToiServlet/detail?maHD=${ghct.maHD}"><i class="fas fa-eye">Xem chi tiết</i></a>
                    </div>
                    <div class="card-body">
                        <div class="row align-items-center">
                            <div class="col-md-1 col-2">
                                <img src="${ghct.srcImage}" alt="ảnh sản phẩm" class="img-fluid d-flex">
                            </div>
                            <div class="col-md-3 col-7">
                                <span class="fw-bold">${ghct.tenSP}</span>
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
                                <label>Tình trạng</label>
                                <span class="text-center text-truncate text-danger">${ghct.tinhTrang}</span>
                            </div>
                            <c:if test="${ghct.tinhTrang eq 'Đang giao hàng'}">
                                <div class="col-md-1 col-2 ms-4">
                                    <form action="/Assignment_Sof3011_war_exploded/TrangCuaToiServlet/update?maHD=${ghct.maHD}" method="post">
                                        <input  type="hidden" name="maHD" value="${ghct.maHD}">
                                        <button type="submit" class="btn ">
                                            <i class="fas fa-edit text-primary">Tôi đã nhận được hàng</i>
                                        </button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
