<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 16.06.2020
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Страница пациента</title>
</head>
<body>

<h1>Выберите врача, к которому Вы хотите попасть на прием</h1>

<c:forEach items="${doctors}" var="doctor">

    <form action="appointment-with-doctor" method="post">

        <div>
            <input type="submit" required name="buDoctor" value="${doctor.surname} ${doctor.name}">
            <input type="hidden" name="doctorId" value="${doctor.id}">
        </div>

    </form>

</c:forEach>

</body>
</html>
