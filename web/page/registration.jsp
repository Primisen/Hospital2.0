<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Регистрация</title>

    <style>
        .registration-form {
            width: 100%;
            max-width: 420px;
            padding: 15px;
            margin: auto;
        }
    </style>
</head>

<body class="text-center">

<c:set scope="request" value="${doctorTypeId}" var="doctorTypeId"></c:set>
<c:set scope="request" value="${nurseTypeId}" var="nurseTypeId"></c:set>

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="/page/header.jsp" %>

    <div class="registration-form">
        <form class="form-signin" action="registration" method="post">

            <h1 class="h3 mb-3 font-weight-normal">Please enter your data</h1>

            <input type="text" class="form-control" required name="name" placeholder="Имя"/>
            <input type="text" class="form-control" required name="surname" placeholder="Фамилия"/>
            <input type="text" class="form-control" required name="login" placeholder="Почта"/>

            <br>

            <input type="password" class="form-control" required name="password" placeholder="Пароль"/>
            <input type="password" class="form-control" required name="passwordConfirmation"
                   placeholder="Подтвердите пароль"/>

            <br>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" requred name="doctorTypeId" value="${doctorTypeId}">Я являюсь
                    доктором</input>
                </label>
                <label>
                    <input type="checkbox" requred name="nurseTypeId" value="${nurseTypeId}">Я являюсь
                    медсестрой </input>
                </label>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегегистрироваться</button>
        </form>

        <a href="/login">У меня уже есть аккаунт</a>
    </div>

    <%@ include file="/page/footer.jsp" %>

</div>
</body>
</html>
