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
        <meta charset="UTF-8" />
        <title>Регистрация</title>

        <link rel="stylesheet" href="styles/w3.css">
    </head>

    <body class="w3-light-grey">

    <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <form action="registration" method="post" >

            <input type="text" required name="name" placeholder="Имя"/>
            <input type="text" required name="surname" placeholder="Фамилия"/>
            <input type="text" required name="login" placeholder="Почта"/>
            <input type="password" required name="password" placeholder="Пароль"/>
            <input type="password" required name="passwordConfirmation" placeholder="Подтвердите пароль"/>

            <div>

                <input type="checkbox" requred name="doctorRole">I'm a doctor</input>
                <br>
                <input type="checkbox" requred name="nurseRole">I'm a nurse</input>

            </div>

            <button>registration</button>
        </form>

        <a href="http://localhost:8080/login">У меня уже есть аккаунт</a>
    </div>
    </body>
</html>
