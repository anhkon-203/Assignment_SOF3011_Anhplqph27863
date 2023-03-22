<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/23/2023
  Time: 2:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">

</head>
<body>
<c:if test="${not empty message}">
  <div class="alert alert-success" role="alert">
      ${message}
  </div>
</c:if>
<div class="container">
  <h1>Register</h1>
  <form method="post" action="/Assignment_Sof3011_war_exploded/LoginServlet/register">
    <div class="form-group">
      <label for="inputHo">Họ:</label>
      <input type="text" class="form-control" id="inputHo" name="ho" required>
    </div>
    <div class="form-group">
      <label for="inputTenDem">Tên đệm:</label>
      <input type="text" class="form-control" id="inputTenDem" name="tenDem" required>
    </div>
    <div class="form-group">
      <label for="inputTen">Tên:</label>
      <input type="text" class="form-control" id="inputTen" name="ten" required>
    </div>
    <div class="form-group">
      <label for="inputNgaySinh">Ngày sinh:</label>
      <input type="date" class="form-control" id="inputNgaySinh" name="ngaySinh" required>
    </div>
    <div class="form-group">
      <label for="inputSdt">Số điện thoại:</label>
      <input type="tel" class="form-control" id="inputSdt" name="sdt" required>
    </div>
    <div class="form-group">
      <label for="inputEmail">Email:</label>
      <input type="email" class="form-control" id="inputEmail" name="email" required>
    </div>
    <div class="form-group">
      <label for="inputMatKhau">Mật khẩu:</label>
      <input type="password" class="form-control" id="inputMatKhau" name="matKhau" required>
    </div>
    <button type="submit" class="btn btn-primary">Đăng ký</button>
  </form>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.min.js"></script>
</body>
</body>
</html>
