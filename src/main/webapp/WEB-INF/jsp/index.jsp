<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  Employee: alexa
  Date: 11.09.2018
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<style>
  .btn:hover {
    background: #9feba6 !important;
    color: #378f92 !important;
    border: 1px solid #9feba6;
  }
</style>
<jsp:include page="fragments/headTag.jsp"/>
<head>
  <title>Стартовая страница</title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron text-center">
  <div class="container">

    <p>Стек технологий: <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
      <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
      <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
      <a href="http://www.slf4j.org/">SLF4J</a>,
      <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
      <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
      <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
      <a href="http://www.webjars.org/">WebJars</a>,
      <a href="http://datatables.net/">DataTables plugin</a>,
      <a href="http://izitoast.marcelodolce.com//">iziToast</a>,
      <a href="http://ehcache.org">Ehcache</a>,
      <a href="http://www.postgresql.org/">PostgreSQL</a>,
      <a href="http://junit.org/">JUnit</a>,
      <a href="http://jquery.com/">jQuery</a>,
      <a href="http://getbootstrap.com/">Bootstrap</a>.</p>
  </div>
</div>
<div class="container">
  <div class="lead">
    &nbsp;&nbsp;&nbsp;<a href="https://github.com/bortmex/LoanSystem">Java Enterprise проект</a> с
    регистрацией/авторизацией и интерфейсом на основе ролей (USER, ADMIN, PARTNER, REPRESENTATIVE, SUPERUSER).
    Администратор может создавать/редактировать/удалять/пользователей. Пользователь может просматривать
    продукты всех Партнеров и создать заявку на кредит у выбранного партнера, выбрав определенные продукты (Только у выбранного Партнера)
    которые его заинтересовали. Партнер может просматривать свои продукты, создавать продукты, просматривать анкеты адресованные ему от
    пользователей (просмотр для Партнера не полный) и принимать решение об одобрении.
    Представитель банка может создавать Партнеров, просматривать все анкеты (только одобренные Партнерами), а так же
    выносить окончательное решение о выдачи кредита пользователю.
    СуперПользователь может просматривать и редактировать все продукты и анкеты.
  </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>