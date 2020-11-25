<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title>Страница доктора</title>

    <style>
        button {
            background-color: #e7e7e7;
            color: black;
            border-radius: 8px;
        }
    </style>
</head>

<body>
<c:set scope="request" value="${doctor}" var="doctor"></c:set>

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <h3>Личная информация</h3>
    <c:out value="${doctor.surname} ${doctor.name}"></c:out>

    <br>

    <form action="treatment">
        <button type="submit">Ваши лечащиеся пациенты</button>
    </form>

    <form action="reception">
        <button type="submit">Ваши пациенты, ожидающие приема</button>
    </form>

    <%@ include file="footer.jsp" %>

</div>
</body>
</html>
