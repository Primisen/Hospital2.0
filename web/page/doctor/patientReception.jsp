<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 05.07.2020
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ваши пациенты, ожидающие приема</h1>

<c:forEach items="${patients}" var="patient">
    <form action="reception" method="post">
        <p>Имя и фамилия пациента:</p>
        <c:out value="${patient.surname} ${patient.name}"></c:out>

        <p>Диагноз:</p>
        <input type="text" name="diagnosis" placeholder="Введите диагноз">

        <p>Тип лечения:</p>
        <select name="treatmentTypeValue">
            <c:forEach items="${treatmentType}" var="treatmentType">
                <option value="${treatmentType}">${treatmentType.russianName}</option>
            </c:forEach>
        </select>

        <p>Количество процедур:</p>
        <input type="text" name="numberOfProcedures" placeholder="Введите количество процедур">

        <br>
        <input type="hidden" name="patientId" value="${patient.id}">
        <input type="submit" name="ok" value="Подтвердить">
    </form>
</c:forEach>

<c:if test="${patients == null}">
    <p>У Вас нет пациентов ожидающих прием.</p>
</c:if>
</body>
</html>
