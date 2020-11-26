<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="page.nurse.title"/> </title>
</head>
<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <c:set var="nurse" value="${nurse}" scope="request"/>
    <h3><fmt:message key="page.common.info"/> </h3>
    <c:out value="${nurse.surname} ${nurse.name}"/>

    <c:forEach items="${nurse.patient}" var="patient">

        <div>

            <c:out value="${patient.surname} ${patient.name}"/>
            <p><fmt:message key="page.nurse.procedures.number"/> : </p>
            <c:out value="${patient.treatment.numberOfTherapies - patient.treatment.numberOfCompletedTherapies}"/>

            <form method="post" action="nurse">
                <input type="submit" required name="patient" value=<fmt:message key="page.nurse.button"/> >
                <input type="hidden" name="patientProcedureKey" value="${patient.id}">
                    <%--naming скрытой кнопки. имя должно передавать предназначение кнопки--%>
            </form>
        </div>

    </c:forEach>

    <c:if test="${patient == null}">
        <p><fmt:message key="page.nurse.message"/> </p>
    </c:if>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
