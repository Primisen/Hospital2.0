<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 08.07.2020
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Доктор</title>
</head>
<body>
<h1>Ваши пациенты</h1>

<c:forEach items="${patients}" var="patient">

    <c:out value="${patient.surname} ${patient.name}"></c:out>

    <h3>Диагноз:</h3>
    <c:out value="${patient.diagnosis}"></c:out>

    <h3>Тип лечения:</h3>
    <c:out value="${patient.treatment.type.russianName}"></c:out>

    <h3>Выполнено:</h3>
    <c:set scope="request" value="${patient.treatment.numberOfCompletedTherapies}"
           var="numberOfCompletedTherapies"></c:set>
    <c:set scope="request" value="${patient.treatment.numberOfTherapies}" var="numberOfTherapies"></c:set>

    <c:out value="${numberOfCompletedTherapies} из ${numberOfTherapies} терапий"></c:out>

    <c:set scope="request" value="${patient.treatment.active}" var="active"></c:set>

    </br>

    <c:if test="${numberOfCompletedTherapies == numberOfTherapies}">
        <form action="treatment" method="post">
            <input type="submit" value="Выписать" name="patientDischarge">
        </form>
    </c:if>

</c:forEach>
</body>
</html>
