<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 16.06.2020
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница доктора</title>
</head>
<body>

<%@ include file="../header.jsp" %>

<c:set scope="request" value="${doctor}" var="doctor"></c:set>

<c:out value="${doctor.surname}"></c:out>
<c:out value="${doctor.name}"></c:out>

<form action="treatment">
    <input type="submit" value="Ваши лечащиеся пациенты">
</form>

<form action="reception">
    <input type="submit" value="Ваши пациенты, ожидающие приема">
</form>

</body>
</html>
