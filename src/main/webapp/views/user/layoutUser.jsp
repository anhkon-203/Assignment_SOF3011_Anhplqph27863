<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/21/2023
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
<html>
<head>
    <title>Title</title>
</head>
<body>
<header class="d-flex align-items-center">
    <div class="col-2 mt-3">
        <a class="navbar-brand col-2 fw-bold " href="/Assignment_Sof3011_war_exploded/SanPhamUserServlet">
            <img class="img-fluid w-50" src="/Assignment_Sof3011_war_exploded/img/logo.png" alt="Logo">
        </a>
    </div>
    <form class="d-flex col-4 mt-3" role="search">
        <input class="form-control" type="search" placeholder="Phiên bản giới hạn mùa lễ hội" aria-label="Search">
    </form>
    <nav class="navbar navbar-expand-lg bg-body-tertiary col-6 d-flex justify-content-end">

        <c:if test="${user == null}">
            <a class="nav-link"
               href="/Assignment_Sof3011_war_exploded/LoginServlet/login">
                Đăng nhập
            </a>
        </c:if>
        <c:if test="${user != null}">
            <a class="nav-link active me-3" aria-current="page" href="/Assignment_Sof3011_war_exploded/TrangCuaToiServlet/index">Trang của tôi</a>
        </c:if>
        <c:if test="${user != null}">
            <div class="shopping-cart d-flex align-items-center">
                <a href="/Assignment_Sof3011_war_exploded/GioHangUserServlet/index" class="btn btn-primary" >
                    <img src="/Assignment_Sof3011_war_exploded/img/icons8-shopping-cart-30.png" alt="Shopping cart"></a>
            </div>
        </c:if>
    </nav>
</header>
<%--  --%>
<div class="row mt-3 mb-3">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand active fw-bold" href="#index">Trang chủ</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="#product">Sản phẩm</a>
                    <a class="nav-link" href="#introduce">Giới thiệu cửa hàng</a>
                    <a class="nav-link" href="#event">Sự kiện</a>
                </div>
                <div class="navbar-nav ms-auto">
                    <c:if test="${user != null}">
                        <span class="text-success mt-2">Xin chào</span>
                        <a class="nav-link" href="#profile">
                                ${user.getHo()} ${user.getTenDem()} ${user.getTen()}
                        </a>
                        <a class="nav-link" href="/Assignment_Sof3011_war_exploded/LoginServlet/logout">Đăng xuất</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</div>

<%-- content --%>
<div class="container">
    <jsp:include page="${view}"/>
</div>
<%-- footer --%>
<div class="row mt-5" id="footer">
    <div class="col-12 col-md-4">
        <p class="mt-3 ms-4"><img src="/Assignment_Sof3011_war_exploded/img/logo_foot.png" alt="innisfree"></p>
        <div class="row col-7 ms-4 ">
            <a href="https://innisfreevietnam.co/2Ab0EGA" class="facebook col-1">
                <img src="/Assignment_Sof3011_war_exploded/img/fb.png" class="img-fluid" alt="">
            </a>
            <a href="https://www.instagram.com/innisfreevietnam/?hl=vi" class="instagram col-1">
                <img src="/Assignment_Sof3011_war_exploded/img/ig.png" class="img-fluid" alt="">
            </a>
            <a href="https://innisfreevietnam.co/3eBPmud" class="youtube col-1">
                <img src="/Assignment_Sof3011_war_exploded/img/youtube.png" class="img-fluid " alt="">
            </a>
            <a href="https://innisfreevietnam.co/3awmEtB" class="zalo col-1">
                <img src="/Assignment_Sof3011_war_exploded/img/zalo.png" class="img-fluid " alt="">
            </a>
            <div class="col-7">
                <p>ⓒ 2020 innisfree Inc. <br>All rights reserved.</p>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-8 row">
        <div class="col-12 col-md-3">
            <h5 class="fw-bold">Về innisfree</h5>
            <a href="" class="text-decoration-none  text-white fs-6">Chính sách giao hàng và
                thanh toán</a><br>
            <a href="" class="text-decoration-none text-white ">Chính sách bảo mật thông tin
                khách hàng</a><br>
            <a href="" class="text-decoration-none text-white">Chính sách mua hàng</a><br>
            <a href="" class="text-decoration-none text-white">Chính sách trả hàng</a><br>
        </div>
        <div class="col-12 col-md-3">
            <h5 class="fw-bold">Liên hệ</h5>
            <a href="tel:02838279777" class="text-decoration-none text-white">Tư vấn khách hàng: 028 3827 9777</a><br>
            <a href="mailto:cs_vn@innisfree.com" class="text-decoration-none text-white">Email: cs_vn@innisfree.com</a><br>
            <p>CÔNG TY TNHH AMOREPACIFIC VIỆT NAM</p>
            <p>GIẤY CNĐKDN: 0309984165 - Ngày cấp: 05/05/2010, được sửa đổi lần thứ 21, ngày 04/04/2022</p>
            <p>Địa chỉ đăng ký kinh doanh: Lầu 4A, Toà nhà Vincom, 72 Lê Thánh Tôn, Phường Bến Nghé, Quận 1,
                Tp. Hồ Chí Minh, Việt Nam.</p>
        </div>
        <div class="col-12 col-md-6">
            <h5 class="fw-bold">Kết nối với innisfree</h5>
            <p>Đăng ký để nhận được thông tin mới nhất từ innisfree</p>
            <form>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="email" placeholder="name@example.com">
                    <label for="email">Địa chỉ email của bạn</label>
                </div>
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </form>
        </div>
    </div>
</div>
<script src="/Assignment_Sof3011_war_exploded/js/bootstrap.min.js"></script>
</body>
<style>
    .card {
        border: none;
    }

    #footer {
        background-color: seagreen;
        color: white;
    }

    .shopping-cart {
        position: relative;
        display: inline-block;
        cursor: pointer;
    }

    .shopping-cart i {
        font-size: 2rem;
        color: blue;
    }

    .shopping-cart .cart-items {
        color: red;
        padding: 0.375rem 0.75rem;
        border-radius: 50%;
        position: absolute;
        top: -8px;
        right: -8px;
        font-size: 1.125rem;
    }
</style>
</html>