<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title>Страница пациента</title>
</head>

<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <c:if test="${patient.receptionDoctor == null}">

        <h1>Выберите врача, к которому Вы хотите попасть на прием</h1>

        <c:forEach items="${doctors}" var="doctors">
            <form action="appointment-with-doctor" method="post">
                <div>
                    <input type="submit" required name="doctorData" value="${doctors.surname} ${doctors.name}">
                    <input type="hidden" name="doctorName" value="${doctors.name}">
                    <input type="hidden" name="doctorSurname" value="${doctors.surname}">
                    <input type="hidden" name="doctorId" value="${doctors.id}">
                </div>
            </form>
        </c:forEach>
    </c:if>

    <c:set scope="request" value="${patient}" var="patient"/>

    <c:if test="${patient.receptionDoctor != null}">
        <h1>Вы успешно записаны к врачу на прием! Ожидайте, пока Вас смогут принять.</h1>

        <form action="patient" method="get">
            <input type="submit" value="Вернуться на главную страницу">
        </form>
    </c:if>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
