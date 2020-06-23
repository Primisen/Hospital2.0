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
    <h1>Ваши пациенты</h1>

    <c:forEach items="${patients}" var="patient">

        <li>
            ${patient.name}
        </li>

    </c:forEach>
</body>
</html>
