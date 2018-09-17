<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 13.09.2018
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/employeesDatatables.js" defer></script>
<head>
    <title>Все сотредники</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Все сотредники</h2>
                </div>
                <div class="col-sm-6">
                    <a onclick="add()" class="btn btn-default"><i class="material-icons">&#xE147;</i> <span>Добавление нового сотрудника</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover display" id="datatable">
            <thead>
            <tr>
                <th>ФИО сотрудника</th>
                <th>Отдел</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <form class="form-horizontal" id="detailsForm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title" id="modalTitle"></h2>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group" id="group1">
                        <label for="name" class="control-label col-xs-3">ФИО:</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="ФИО">
                        </div>
                    </div>

                    <div class="form-group" id="group4">
                        <label for="dep" class="control-label col-xs-3">Отделы</label>

                        <div class="col-xs-9">
                            <select class="form-control" id="dep">
                                <c:forEach items="${allDepartment}" var="department">
                                    <option id="${department.id}">${department.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>

                </div>

                <div class="modal-footer" id="group5">
                    <button type="submit" class="btn btn-success">Сохранить</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>