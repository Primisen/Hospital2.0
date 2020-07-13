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
    <meta charset="UTF-8"/>
    <title>Регистрация</title>

    <link rel="stylesheet" href="../styles/w3.css">
</head>

<body class="w3-light-grey">

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">

    <form action="registration" method="post">

        <input type="text" required name="name" placeholder="Имя"/>
        <input type="text" required name="surname" placeholder="Фамилия"/>
        <input type="text" required name="login" placeholder="Почта"/>
        <input type="password" required name="password" placeholder="Пароль"/>
        <input type="password" required name="passwordConfirmation" placeholder="Подтвердите пароль"/>

        <c:set scope="request" value="staffTypeDoctor" var="doctor"></c:set>
        <c:set scope="request" value="staffTypeNurse" var="nurse"></c:set>

        <input type="checkbox" requred name="typeDoctor" value="${doctor}">I'm a doctor</input>

        <br>

        <input type="checkbox" requred name="typeNurse" value="${nurse}">I'm a nurse</input>

        <input type="submit" value="Зарегестрироваться"/>
    </form>

    <a href="http://localhost:8080/login">У меня уже есть аккаунт</a>
</div>
</body>
</html>
