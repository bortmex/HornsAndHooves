<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 12.09.2018
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-default navbar-expand-lg navbar-light">
    <div class="navbar-header d-flex col">

        <a class="navbar-brand" href="">Рога<b>Копыта</b></a>
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse"
                class="navbar-toggle navbar-toggler ml-auto">
            <span class="navbar-toggler-icon"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
    </div>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse justify-content-start">
        <ul class="nav navbar-nav navbar-left">
            <li class="nav-item"><a href="<c:url value='/employee'/>" class="nav-link">Сотрудники</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Данные
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Все заказы</a>
                    <a class="dropdown-item" href="#">Незавершенные заказы</a>
                    <a class="dropdown-item" href="#">Заказы по отделам</a>
                    <a class="dropdown-item" href="#">Заказы по сотрудникам</a>
                    <a class="dropdown-item" href="#">Количество дней и часов до окончания заказа.</a>
                </div>
            </li>
            <li class="nav-item"><a href="<c:url value='/orders'/>" class="nav-link">Все заказы</a></li>
        </ul>
</nav>