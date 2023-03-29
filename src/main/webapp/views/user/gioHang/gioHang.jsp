<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/22/2023
  Time: 2:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
    <style>
        .card-header {
            background-color: #f8f9fa;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        #deal {
            background-color: #dc3545;
            color: white;
            padding: 2px 5px;
            border-radius: 5px;
            font-size: 0.9rem;
            font-weight: bold;
            margin-right: 10px;
        }

        #textProduct {
            font-size: 0.9rem;
            color: #777777;
        }

        #priceProduct {
            font-size: 1.2rem;
            font-weight: bold;
            color: #dc3545;
        }

        .input-group {
            width: 70px;
        }

        #quantity-input {
            padding: 7px 5px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            text-align: center;
        }

        .btn-success {
            background-color: #dc3545;
            border: none;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <c:forEach var="ghct" items="${listGioHangChiTiet}" varStatus="status">
                <div class="card mb-2">
                    <div class="card-header">
                        <span id="deal">Deal Sốc</span>
                        <a href="#" class="text-decoration-none text-danger">Thêm</a>
                    </div>
                    <div class="card-body">
                        <div class="row align-items-center">
                            <span class="fw-bold">${ghct.tenSp}</span>
                            <div class="col-1">
                                <img src="${ghct.srcImage}" alt="ảnh sản phẩm" class="img-fluid d-flex">
                            </div>
                            <div class="col-md-3 col-7">
                                <img src="/Assignment_Sof3011_war_exploded/img/freeShip.png" class="img-fluid"
                                     alt="Miễn phí vận chuyển">
                                <img src="/Assignment_Sof3011_war_exploded/img/7.png" class="img-fluid " alt="Miễn phí vận chuyển">
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
                                <form action="/Assignment_Sof3011_war_exploded/GioHangUserServlet/delete" method="GET">
                                    <input type="hidden" name="id" value="${ghct.id}">
                                    <input type="hidden" name="idGioHang" value="${ghct.idGioHang}">
                                    <button class="btn btn-danger" type="submit">Xóa</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-4">
            <form action="/Assignment_Sof3011_war_exploded/HoaDonUserServlet/store" method="post">
                <input type="hidden" name="idKhachHang" value="${user.getId()}">
                <div class="mb-3">
                    <label for="name" class="form-label">Họ tên Người nhận</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Địa chỉ</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="tel" class="form-control" id="phone" name="phone" required>
                </div>
                <button class="btn btn-success" type="submit">Đặt hàng</button>
            </form>
        </div>
    </div>
</div>
    </div>


</body>
</html>