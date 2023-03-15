<%--
  Created by IntelliJ IDEA.
  User: anhkon
  Date: 3/11/2023
  Time: 4:26 PM
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
<h1>Thông tin Sản phẩm</h1>
<a href="/Assignment_Sof3011_war_exploded/mau-sac/create" class="btn btn-success mt-3">Add</a>
<c:if test="${ f:length(list) == 0 }">
    <h4 class="text-center">Không có dữ liệu</h4>
</c:if>

<c:if test="${ f:length(list) != 0 }">

    <table class="table table-bordered mt-5">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên</th>
<%--            <th class="col-2 text-center">Action</th>--%>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="mauSac" items="${ list }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${ mauSac.ma }</td>
                <td>${ mauSac.ten }</td>

                    <%--                <td class="text-center">--%>
                    <%--                    <a href="/Assignment_Sof3011_war_exploded/admin/nsx/edit?ma=${ nsx.ma }"--%>
                    <%--                       class="btn btn-primary">Update</a>--%>
                    <%--                    <a href="/Assignment_Sof3011_war_exploded/admin/nsx/delete?ma=${ nsx.ma }"--%>
                    <%--                       class="btn btn-danger">Delete</a>--%>
                    <%--                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
