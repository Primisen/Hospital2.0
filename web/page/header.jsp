<%--
  Created by IntelliJ IDEA.
  User: primi
  Date: 03.07.2020
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--    <title>Title</title>--%>
<%--    <%@ page %> --%>
</head>
<body>

<c:if test="${sessionScope.user == null}">
    <form action="registration">
        <input type="submit" value="Регистрация"/>
    </form>

    <form action="login">
        <input type="submit" value="Войти"/>
    </form>
</c:if>

<c:if test="${sessionScope.user != null}">

    <form action="logout" method="post">
        <input type="submit" value="Выйти">
    </form>

</c:if>

</body>
</html>
