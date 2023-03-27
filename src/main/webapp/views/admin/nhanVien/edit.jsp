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
    <h1>Update Nhân Viên</h1>
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <form method="POST"
          action="/Assignment_Sof3011_war_exploded/nhan-vien/update?ma=${nhanVien.ma}">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${nhanVien.ma}"disabled />
            </div>
            <div class="col-6">
                <label>Họ</label>
                <input type="text" name="ho" class="form-control"  value="${nhanVien.ho}"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Tên đệm</label>
                <input type="text" name="tenDem" class="form-control"  value="${nhanVien.tenDem}"/>
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control"  value="${nhanVien.ten}"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Ngày sinh</label>
                <input type="date" name="ngaySinh" class="form-control"  value="${nhanVien.ngaySinh}"/>
            </div>
            <div class="col-6">
                <label>SDT</label>
                <input type="tel" name="sdt" class="form-control"  value="${nhanVien.sdt}"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <label>Địa chỉ</label>
                <input type="text" name="diaChi" class="form-control "  value="${nhanVien.diaChi}"/>
            </div>
            <div class="col-6">
                <label>Mật khẩu</label>
                <input type="password" name="matKhau" class="form-control"  value="${nhanVien.matKhau}"/>
            </div>

        </div>

        <div class="row mt-3">
            <div class="col-6">
                <label>Email</label>
                <input type="email" name="email" class="form-control" value="${nhanVien.email}"/>
            </div>
            <div class="col-6">
                <label>Chức vụ</label>
                <select class="form-control" name="idChucVu" required>
                    <c:forEach items="${listChucVu}" var="chucVu">
                        <option value="${chucVu.id}" ${chucVu.id == idCv ? "selected" : ""}>${chucVu.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-6">
                <label>Trạng thái</label>
                <div class="d-flex">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="trangThai"  value="1"${nhanVien.trangThai == 1 ? "checked" : ""} >
                        <label class="form-check-label">
                            Đang làm
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="trangThai" value="0" ${nhanVien.trangThai == 0 ? "checked" : ""}>
                        <label class="form-check-label">
                            Đã nghỉ
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <label>Giới tính</label>
                <div class="d-flex">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gioiTinh" value="Nam" ${nhanVien.gioiTinh == "Nam" ? "checked" : ""}>
                        <label class="form-check-label">
                            Nam
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gioiTinh"  value="Nữ"  ${nhanVien.gioiTinh == "Nữ" ? "checked" : ""}>
                        <label class="form-check-label">
                            Nữ
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <label>Cửa hàng</label>
                <select class="form-control"  name="idCuaHang" required>
                    <c:forEach items="${listCuaHang}" var="cuaHang">
                        <option value="${cuaHang.id}" ${cuaHang.id == idCh ? "selected" : ""}>${cuaHang.ten}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Update</button>
            </div>
            <div class="col-6">

            </div>
        </div>
    </form>

</div>

</body>
</html>
