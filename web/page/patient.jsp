<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Страница пациента</title>
    <%--    <link rel="stylesheet" href="../styles/bootstrap-4.5.3-examples/carousel/carousel.css">--%>
</head>
<body>

<c:set scope="request" value="${patient}" var="patient"/>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%--<div class="container">--%>
    <%@ include file="header.jsp" %>

    <main role="main" class="container">

        <h3>Личная информация</h3>
        <p><c:out value="${patient.surname}"/>
            <c:out value="${patient.name}"/></p>

        <div class="row">
            <div class="col-md-8 blog-main">
                <c:if test="${patient.diagnosis != null}">
                    <h3>Диагноз</h3>
                    <p><c:out value="${patient.diagnosis}"/></p>
                    <h3>Тип лечения:</h3>
                    <p><c:out value="${patient.treatment.type.russianName}"/></p>
                    <h3>Статус лечения:</h3>
                    <p>Пациент проходит лечение</p>
                    <c:if test="${patient.treatment.active == false}">
                        <p>Лечение пройдено</p>
                    </c:if>
                    <h3>Лечащий доктор</h3>
                    <p><c:out value="${patient.treatingDoctor.name}"/>
                        <c:out value="${patient.treatingDoctor.surname}"/></p>
                </c:if>
            </div>
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

    </main>
    <%@ include file="footer.jsp" %>
</div>

</body>
</html>
