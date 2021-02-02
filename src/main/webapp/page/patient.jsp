<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="page.patient.title"/></title>
    <%--    <link rel="stylesheet" href="../styles/bootstrap-4.5.3-examples/carousel/carousel.css">--%>
</head>
<body>

<c:set scope="request" value="${patient}" var="patient"/>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%--<div class="container">--%>
    <%@ include file="header.jsp" %>

    <main role="main" class="container">

        <h3><fmt:message key="page.common.info"/></h3>
        <p><c:out value="${patient.surname}"/>
            <c:out value="${patient.name}"/></p>

        <div class="row">
            <div class="col-md-8 blog-main">
                <c:if test="${patient.diagnosis != null}">

                    <h3><fmt:message key="page.patient.diagnosis"/>: </h3>
                    <p><c:out value="${patient.diagnosis}"/></p>
                    <h3><fmt:message key="page.patient.TreatmentType"/>: </h3>

                    <%--!!--%>
                    <p><c:out value="${patient.treatment.type.russianName}"/></p>

                    <h3><fmt:message key="page.patient.TreatmentStatus"/>:</h3>
                    <p><fmt:message key="page.patient.TreatmentStatus.active"/> </p>
                    <c:if test="${patient.treatment.active == false}">
                        <p><fmt:message key="page.patient.TreatmentStatus.NotActive"/> </p>
                    </c:if>
                    <h3><fmt:message key="page.patient.TreatmentDoctor"/>: </h3>
                    <p><c:out value="${patient.treatingDoctor.name}"/>
                        <c:out value="${patient.treatingDoctor.surname}"/></p>
                </c:if>
            </div>
        </div>

        <c:if test="${patient.receptionDoctor == null}">
            <form action="appointment-with-doctor" method="get">
                <div>
                    <input type="submit" value=<fmt:message key="page.patient.doctor.appointment.message"/> >
                </div>
            </form>
        </c:if>

        <c:if test="${(patient.receptionDoctor != null) && (patient.diagnosis == null)}">
            <br><fmt:message key="page.patient.doctor.appointment"/> ${patient.receptionDoctor.surname} ${patient.receptionDoctor.name}.
            </br><fmt:message key="page.patient.appointment"/> </h3>
        </c:if>

    </main>
    <%@ include file="footer.jsp" %>
</div>

</body>
</html>
