<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="l10n"/>

<body background="">
<ul>
    <li><a href="?cookieLocale=ru"><fmt:message key="label.lang.ru"/></a></li>
    <li><a href="?cookieLocale=en"><fmt:message key="label.lang.en"/></a></li>
</ul>

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

