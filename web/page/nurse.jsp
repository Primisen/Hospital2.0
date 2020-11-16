<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 16.06.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница медсестры</title>
</head>
<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <c:set var="nurse" value="${nurse}" scope="request"></c:set>
    <h3>Личная информация</h3>
    <c:out value="${nurse.surname} ${nurse.name}"></c:out>

    <c:forEach items="${nurse.patientTherapies}" var="patientTherapies">

        <div>

            <c:out value="${patientTherapies.key.surname} ${patientTherapies.key.name}"></c:out>
            <c:out value="Количество оставшихся процедур: ${patientTherapies.value}"></c:out>

            <form method="post" action="nurse">
                <input type="submit" required name="patient" value="Выполнить процедуру">
                <input type="hidden" name="patientProcedureKey" value="${patientTherapies.key.id}">
                    <%--naming скрытой кнопки. имя должно передавать предназначение кнопки--%>
            </form>
        </div>

    </c:forEach>

    <c:if test="${patientTherapies == null}">
        <p>Вам не назначены пациенты.</p>
    </c:if>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
