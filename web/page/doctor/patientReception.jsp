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

    <form action="reception" method="post">

        <c:out value="${patient.surname} ${patient.name}"></c:out>

        <P>диагноз</P>
        <input type="submit" name="diagnosis" placeholder="введите диагноз">

        <p>Тип лечения</p>
        <select>
            <c:forEach var="treatmentType" items="${treatmentType}">
                <option><c:out value="${treatmentType}"/></option>
            </c:forEach>
        </select>

        <p>Количество процедур</p>
        <c:out value="${patient.surname} ${patient.name}"></c:out>


    </form>

</c:forEach>

<%--<c:set scope="request" value="${patients}" var="patient"--%>

<c:if test="${patient == null}">
    <p>У Вас нет пациентов ожидающих прием.</p>
</c:if>
</body>
</html>
