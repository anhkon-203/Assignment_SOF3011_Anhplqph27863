<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
<%--<h1><%= "Hello World!" %>--%>
</h1>
<br/>
<div class="container">
<%--    <h2>Các liên kết</h2>--%>
    <form>
        <div class="form-group">
            <label for="store-link">Sản phẩm:</label>
            <a href="san-pham/store" class="form-control" >Sản phẩm</a>
        </div>
        <div class="form-group">
            <label for="color-link">Màu sắc:</label>
            <a href="mau-sac/store" class="form-control" id="color-link">Màu sắc</a>
        </div>
        <div class="form-group">
            <label for="customer-link">Khách hàng:</label>
            <a href="khach-hang/store" class="form-control" id="customer-link">Khách hàng</a>
        </div>
        <div class="form-group">
            <label for="product-line-link">Dòng Sản phẩm:</label>
            <a href="dong-san-pham/store" class="form-control" id="product-line-link">Dòng Sản phẩm</a>
        </div>
        <div class="form-group">
            <label for="store-link">Cửa hàng:</label>
            <a href="cua-hang/store" class="form-control" id="store-link">Cửa hàng</a>
        </div>
        <div class="form-group">
            <label for="manufacturer-link">Nhà sản xuất:</label>
            <a href="nsx/store" class="form-control" id="manufacturer-link">Nhà sản xuất</a>
        </div>
        <div class="form-group">
            <label for="product-detail-link">Chi tiết Sản phẩm:</label>
            <a href="chi-tiet-san-pham/store" class="form-control" id="product-detail-link">Chi tiết Sản phẩm</a>
        </div>
    </form>
</div>




</body>
</html>