<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 08.07.2020
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Доктор</title>
</head>
<body>
<h1>Ваши пациенты</h1>

<c:forEach items="${patients}" var="patient">

<%--    <form action="patient-reception" method="post">--%>
        <input type="submit" name="rrr" value="${patient.surname} ${patient.name}">
<%--    </form>--%>

</c:forEach>
</body>
</html>
