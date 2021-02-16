<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="doctor.reception.title"/></title>
    <style>
        .patient-form {
            color: #000000;
            margin-bottom: 20px;
        }

        h4, h3, p {
            text-shadow: none;
        }
    </style>
</head>

<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <h1><fmt:message key="doctor.reception.message"/></h1>

    <c:if test="${empty patients}">

        <p><fmt:message key="doctor.reception.patient.message"/></p>

        <form action="doctor">
            <button type="submit"><fmt:message key="doctor.button.return"/></button>
        </form>

    </c:if>

    <c:forEach items="${patients}" var="patient">

        <form class="patient-form" action="checkup" method="post">
            <div class="card mb-4 shadow-sm patient-form">

                <div class="card-header">
                    <h4><c:out value="${patient.surname} ${patient.name}"/></h4>
                </div>

                <div class="card-body">
                    <p><fmt:message key="doctor.reception.diagnosis"/> : </p>
                    <input type="text" name="diagnosis" placeholder="Введите диагноз">

                    <p><fmt:message key="doctor.reception.treatment.type"/> :</p>
                    <select name="treatmentTypeValue">
                        <c:forEach items="${treatmentType}" var="treatmentType">
                            <option value="${treatmentType}">${treatmentType.russianName}</option>
                        </c:forEach>
                    </select>

                    <p><fmt:message key="doctor.reception.procedure.number"/> :</p>
                    <input type="text" name="numberOfProcedures" placeholder="Введите количество процедур">

                    <br>
                    <input type="hidden" name="patientId" value="${patient.id}">
                    <input type="submit" name="submit_button" value=
                        <fmt:message key="doctor.reception.button"/>>
                </div>
            </div>
        </form>
    </c:forEach>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
