<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 05.07.2020
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Ваши пациенты, ожидающие приема</h1>

<c:forEach items="${patients}" var="patient">

    <form action="/patient-acceptance/*" method="post">
        <input type="submit" name="patient" value="${patient.surname} ${patient.name}">
    </form>

</c:forEach>
</body>
</html>
