<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 11.06.2020
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--loginFrom.html-->
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Войти</title>
    </head>

    <body>

        <form action="login" method="post">

            <input type="text" required name="login" placeholder="Логин"/>
            <input type="password" required name="password" placeholder="Пароль"/>

            <input type="submit" value="Войти"/>
        </form>

        <a href="/registration">У меня нет аккаунта</a>

    </body>
</html>
