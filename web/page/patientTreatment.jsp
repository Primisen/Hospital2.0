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

    <style>
        .patient-form{
            color: #000000;
            margin-bottom: 20px;
        }

        h4, h3, p{
            text-shadow: none;
        }
    </style>
</head>
<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <h1>Ваши пациенты</h1>

    <c:set scope="request" value="${patientList}" var="patients"/>
    <c:if test="${patients == null}">
        <p>Список пациентов пуст.</p>
    </c:if>

    <c:forEach items="${patients}" var="patient">

        <div class="card mb-4 shadow-sm patient-form">
            <div class="card-header">
                <h4 ><c:out value="${patient.surname} ${patient.name}"/></h4>
            </div>
            <div class="card-body">
                <h3>Диагноз:</h3>
                <p><c:out value="${patient.diagnosis}"/></p>

                <h3>Тип лечения:</h3>
                <p><c:out value="${patient.treatment.type.russianName}"/></p>

                <h3>Выполнено:</h3>
                <c:set scope="request" value="${patient.treatment.numberOfCompletedTherapies}"
                       var="numberOfCompletedTherapies"/>
                <c:set scope="request" value="${patient.treatment.numberOfTherapies}" var="numberOfTherapies"/>
                <p><c:out value="${numberOfCompletedTherapies} из ${numberOfTherapies} терапий"/></p>


                <c:set scope="request" value="${patient.treatment.active}" var="active"/>
                <c:if test="${numberOfCompletedTherapies == numberOfTherapies}">
                    <form action="treatment" method="post">
                        <input type="submit" value="Выписать" name="patientDischarge">
                    </form>
                </c:if>
            </div>
        </div>

    </c:forEach>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
