<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 30.06.2020
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница пациента</title>
</head>
<body>

    <%@ include file="../header.jsp"%>

    <c:set scope="request" value="${patient}" var="patient"></c:set>

        <div>
            <h3>Личная информация</h3>
            <c:out value="${patient.surname}"></c:out>
            <c:out value="${patient.name}"></c:out>

            <c:if test="${patient.diagnosis != null}">
                <h3>Статус лечения</h3>
                <c:out value="${patient.diagnosis}"></c:out>
                <c:out value="${patient.treatment.type}"></c:out> <%-- ! --%>

                <c:if test="${patient.treatment.active == true}">
                    <p>Пациент проходит лечение</p>
                </c:if>
<%--                <c:out value="${patient.treatment.active}"></c:out>--%>
                <c:if test="${patient.treatment.treatmentIsDone == true}">
                    <p>Лечение пройдено</p>
                </c:if>
<%--                <c:out value="${patient.treatment.treatmentIsDone}"></c:out>--%>

                <h3>Лечащий доктор</h3>
                <c:out value="${patient.treatingDoctor.name}"></c:out>
                <c:out value="${patient.treatingDoctor.surname}"></c:out>
            </c:if>
        </div>

    <c:if test="${patient.diagnosis == null && patient.receivingDoctor == null}">

        <form action="appointment-with-doctor">
            <div>
                <input type="submit" value="Записаться на прием к врачу">
            </div>
        </form>

    </c:if>


    <c:if test="${(patient.receivingDoctor != null) && (patient.diagnosis == null)}">  <%--не слишкол ли много логики для jsp??? --%>

        <br>Вы записаны на прием к ${patient.receivingDoctor.surname} ${patient.receivingDoctor.name}.</br>Ожидайте, пока доктор сможет Вас принять.</h3>
    </c:if>

</body>
</html>
