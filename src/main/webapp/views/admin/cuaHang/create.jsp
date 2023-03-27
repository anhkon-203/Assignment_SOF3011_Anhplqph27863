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

<div class="col-8 offset-2">
    <h1>Thêm mới Cửa hàng</h1>
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <form method="POST"
          action="/Assignment_Sof3011_war_exploded/cua-hang/store">
            <div class="mt-3">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" />
            </div>
            <div class="mt-3">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" />
            </div>
            <div class="mt-3">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control"/>
            </div>
            <div class="mt-3">
                <label>Quốc gia</label>
                <select name="quocGia" class="form-select">
                    <option value="vi">Việt Nam</option>
                    <option value="us">Mỹ</option>
                </select>
            </div>
            <div class="mt-3">
                <label>Thành phố</label>
                <select name="thanhPho" class="form-select">
                    <option value="ha_noi">Hà Nội</option>
                    <option value="new_york">New York</option>
                </select>
            </div>
            <div class="mt-3">
                <button class="btn btn-primary">Thêm mới</button>
            </div>
    </form>
</div>

</body>
</html>
