<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="doctor.treatment.title"/></title>

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

    <h1><fmt:message key="doctor.treatment.message"/></h1>

    <c:set scope="request" value="${patientsUndergoingTreatment}" var="patients"/>

    <c:if test="${empty patients}">
        <p><fmt:message key="doctor.treatment.message.patient.list.empty"/></p>

        <form action="doctor">
            <button type="submit"><fmt:message key="doctor.button.return"/></button>
        </form>
    </c:if>

    <c:forEach items="${patients}" var="patient">

        <div class="card mb-4 shadow-sm patient-form">
            <div class="card-header">
                <h4><c:out value="${patient.surname} ${patient.name}"/></h4>
            </div>
            <div class="card-body">
                <h3><fmt:message key="doctor.treatment.diagnosis"/></h3>
                <p><c:out value="${patient.diagnosis}"/></p>

                <h3><fmt:message key="doctor.treatment.type"/></h3>
                <p><c:out value="${patient.treatment.type.russianName}"/></p>

                <h3><fmt:message key="doctor.treatment.done"/></h3>
                <c:set scope="request" value="${patient.treatment.numberOfCompletedTherapies}"
                       var="numberOfCompletedTherapies"/>
                <c:set scope="request" value="${patient.treatment.numberOfTherapies}" var="numberOfTherapies"/>

                    <%--!!--%>
                <p><c:out value="${numberOfCompletedTherapies} / ${numberOfTherapies} "/></p>


                <c:set scope="request" value="${patient.treatment.active}" var="active"/>
                <c:if test="${numberOfCompletedTherapies == numberOfTherapies}">
                    <form action="treatment" method="post">
                        <input type="submit" value=
                            <fmt:message key="doctor.treatment.button.discharge"/> name="patientDischarge">
                    </form>
                </c:if>
            </div>
        </div>

    </c:forEach>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
