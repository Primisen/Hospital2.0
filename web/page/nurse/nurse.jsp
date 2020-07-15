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

<%@ include file="../header.jsp" %>

<c:set var="nurse" value="${nurse}" scope="request"></c:set>
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
</body>
</html>
