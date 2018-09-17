<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 13.09.2018
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orderDatatables.js" defer></script>
<head>
    <title>Все ордера</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Все ордера</h2>
                </div>
                <div class="col-sm-6">

                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="datatable">
            <thead>
            <tr>
                <th>Имя ордера</th>
                <th>Тип мебели</th>
                <th>Дата создания ордера</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>