<%-- This file was created using IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập hoặc đăng ký thành viên</title>
    <link rel="stylesheet" href="/Assignment_Sof3011_war_exploded/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Đăng nhập hoặc đăng ký thành viên</h2>
    <hr>
    <div class="timeline">
        <div class="row">
            <div class="col-6 text-center mt-5">
                <form method="POST" action="/Assignment_Sof3011_war_exploded/LoginServlet/login">
                    <h3>Đăng nhập thành viên</h3>
                    <div class="input-group mb-3 pe-5">
                        <input type="text" class="form-control" placeholder="ex: 0582434619/phamlequyenanh1308@gmail.com" aria-label="Username" name="email">
                    </div>
                    <div class="input-group mb-3 pe-5">
                        <input type="password" class="form-control" placeholder="Vui lòng nhập mật khẩu" aria-label="Password" name="matKhau">
                    </div>
                    <button class="btn btn-success w-75 h-30 mt-3 fs-4">Đăng nhập</button>
                    <p class="fw-semibold mt-2">Đăng nhập nhanh/Đăng ký thành viên bằng tài khoản mạng xã hội:</p>
                    <a class="btn text-white me-2" href="#" role="button" style="background-color: #3B5998;">Facebook</a>
                    <a class="btn text-white" href="#" role="button" style="background-color: #EA4335;">Google</a>
                    <br>
                    <span>
                                <p>* Có thể đăng nhập nhanh hoặc đăng ký thành viên mới bằng tài khoản mạng xã hội.</p>
                                <p>* Thành viên Innisfree chưa liên kết với tài khoản mạng xã hội có thể đăng nhập và thiết lập liên kết một cách nhanh chóng</p>
                            </span>
                </form>
                <a href="/Assignment_Sof3011_war_exploded/LoginServlet/forgot-password" class="text-decoration-none col-10 offset-8">
                    <span class="text-primary">Quên mật khẩu?</span>
                </a>
            </div>
            <div class="col-5 text-center ps-5 mt-5">
                <h3>Đăng ký thành viên mới</h3>
                <br>
                <span class="ms-3">Đăng ký ngay để mua sắm dễ dàng hơn và tận hưởng thêm nhiều ưu đãi độc quyền cho thành viên Innisfree.</span>
                <br>
                <button type="button" class="btn btn-primary">Đăng ký tài khoản</button>
            </div>
        </div>
    </div>
</div>
<script src="/Assignment_Sof3011_war_exploded/js/bootstrap.min.js"></script>
</body>
</html>