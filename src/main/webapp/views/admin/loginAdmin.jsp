<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 4/6/2023
  Time: 12:22 AM
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
  <form action="/Assignment_Sof3011_war_exploded/LoginAdminServlet/check" method="post">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h4 class="text-center">Đăng nhập</h4>
            </div>
            <div class="card-body">
              <form>
                <div class="form-group">
                  <label for="email">Email</label>
                  <input type="email" class="form-control" name="email" required>
                </div>
                <div class="form-group">
                  <label for="password">Mật khẩu</label>
                  <input type="password" class="form-control" name="matKhau" required>
                </div>
                <c:if test="${not empty sessionScope.error}">
                  <div class="alert alert-danger" role="alert">
                      ${sessionScope.error}
                  </div>
                  <% session.removeAttribute("error"); %>
                </c:if>
                <div class="form-group text-center">
                  <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    </form>
</body>
</html>
