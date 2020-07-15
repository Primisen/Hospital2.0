<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 05.06.2020
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>

<body>

<div>

    <c:set scope="request" value="${doctorTypeId}" var="doctorTypeId"></c:set>
    <c:set scope="request" value="${nurseTypeId}" var="nurseTypeId"></c:set>

    <form action="registration" method="post">

        <input type="text" required name="name" placeholder="Имя"/>
        <input type="text" required name="surname" placeholder="Фамилия"/>
        <input type="text" required name="login" placeholder="Почта"/>
        <input type="password" required name="password" placeholder="Пароль"/>
        <input type="password" required name="passwordConfirmation" placeholder="Подтвердите пароль"/>

        <input type="checkbox" requred name="doctorTypeId" value="${doctorTypeId}">Я являюсь доктором</input>
        <input type="checkbox" requred name="doctorTypeId" value="${nurseTypeId}">Я являюсь медсестрой </input>

        <input type="submit" value="Зарегестрироваться"/>
    </form>

    <a href="/login">У меня уже есть аккаунт</a>
</div>
</body>
</html>
