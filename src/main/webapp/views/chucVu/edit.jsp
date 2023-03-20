<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/9/2023
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>

<div class="col-8 offset-2">
    <h1>Update chức vụ</h1>
    <form method="POST"
          action="/Assignment_Sof3011_war_exploded/chuc-vu/update?ma=${chucVu.ma}">
            <div class="mt-3">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${chucVu.ma}" disabled />
            </div>
            <div class="mt-3">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control"  value="${chucVu.ten}" />
            </div>
            <div class="mt-3">
                <button class="btn btn-primary">Thêm mới</button>
            </div>
    </form>
</div>

</body>
</html>
