<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
<div>
  <h2 class="mt-4 container">
    Lấy lại mật khẩu
  </h2>
  <hr>
  <div class="container">
    <form method="post" action="/forgot_password.jsp">
      <div class="row mb-3">
        <div class="col-8">
          <input type="email" class="form-control" placeholder="Nhập địa chỉ email" name="email">
        </div>
        <div class="col-4">
          <button type="submit" class="btn btn-primary">Gửi</button>
        </div>
      </div>

      <div class="row mb-3">
        <div class="col-8">
          <input type="text" class="form-control" placeholder="Nhập mã xác nhận" name="code">
        </div>
        <div class="col-4">
          <button type="submit" class="btn btn-primary">Xác nhận</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body>
</html>
