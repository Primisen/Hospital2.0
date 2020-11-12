<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Страница пациента</title>
</head>
<body>

    <%@ include file="header.jsp"%>

    <c:set scope="request" value="${patient}" var="patient"/>

        <div>
            <h3>Личная информация</h3>
            <c:out value="${patient.surname}"/>
            <c:out value="${patient.name}"/>

            <c:if test="${patient.diagnosis != null}">
                <h3>Диагноз</h3>
                <c:out value="${patient.diagnosis}"/>
                <h3>Тип лечения:</h3>
                <c:out value="${patient.treatment.type.russianName}"/>

                <c:if test="${patient.treatment.active == true}">
                    <p>Пациент проходит лечение</p>
                </c:if>

                <c:if test="${patient.treatment.active == false}">
                    <p>Лечение пройдено</p>
                </c:if>

                <h3>Лечащий доктор</h3>
                <c:out value="${patient.treatingDoctor.name}"/>
                <c:out value="${patient.treatingDoctor.surname}"/>
            </c:if>
        </div>

    <c:if test="${patient.receptionDoctor == null}">
        <form action="appointment-with-doctor" method="get">
            <div>
                <input type="submit" value="Записаться на прием к врачу">
            </div>
        </form>
    </c:if>


    <c:if test="${(patient.receptionDoctor != null) && (patient.diagnosis == null)}">
        <br>Вы записаны на прием к ${patient.receptionDoctor.surname} ${patient.receptionDoctor.name}.
        </br>Ожидайте, пока доктор сможет Вас принять.</h3>
    </c:if>

</body>
</html>
