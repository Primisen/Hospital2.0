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


<c:forEach items="${patientProcedure}" var="patientProcedure">

    <div>

        <c:out value="${patientProcedure.key.surname} ${patientProcedure.key.name}"></c:out>
        <c:out value="Количество оставшихся процедур: ${patientProcedure.value}"></c:out>

        <form method="post" action="nurse">
            <input type="submit" required name="patient" value="Выполнить процедуру">
            <input type="hidden" name="patientProcedureKey" value="${patientProcedure.key.id}">
                <%--naming скрытой кнопки. имя должно передавать предназначение кнопки--%>
        </form>
    </div>

</c:forEach>
</body>
</html>
