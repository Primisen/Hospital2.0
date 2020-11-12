<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 16.06.2020
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Страница пациента</title>
</head>
<body>

<h1>Выберите врача, к которому Вы хотите попасть на прием</h1>

<c:if test="${patient.receptionDoctor    == null}">

    <c:forEach items="${doctors}" var="doctors">
        <form action="appointment-with-doctor" method="post">
            <div>
                <input type="submit" required name="doctorData" value="${doctors.surname} ${doctors.name}">
                <input type="hidden" name="doctorName" value="${doctors.name}">
                <input type="hidden" name="doctorSurname" value="${doctors.surname}">
                <input type="hidden" name="doctorId" value="${doctors.id}">
            </div>
        </form>
    </c:forEach>
</c:if>

<c:set scope="request" value="${patient}" var="patient"/>

<c:if test="${patient.receptionDoctor != null}">
    <h1>Вы успешно записаны к врачу на прием! Ожидайте, пока Вас смогут принять.</h1>

    <form action = "patient" method="get">
        <input type="submit" value="Вернуться на главную страницу">
    </form>
</c:if>

</body>
</html>
