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
    <a href="/Assignment_Sof3011_war_exploded/san-pham/index"><button class="btn btn-primary col-8 offset-2">Layout Admin</button></a>
    <form action="/Assignment_Sof3011_war_exploded/SanPhamUserServlet" method="get">
       <button class="btn btn-primary col-8 offset-2 mt-3">Layout User </button>
    </form>
</div>
</body>
</html>