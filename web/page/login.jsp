<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="page.login.title"/></title>

    <style>
        .login-form {
            width: 100%;
            max-width: 420px;
            padding: 15px;
            margin: auto;
        }
        .exception {
            color: red;
        }
    </style>
</head>

<body class="text-center">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="/page/header.jsp" %>

    <div class="login-form">

        <div class="exception">
            <c:set scope="request" value="${exceptionMessage}" var="exceptionMessage"/>
            <c:out value="${exceptionMessage}"/>
        </div>

        <form class="form-signin" action="login" method="post">

            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.title"/></h1>

            <%--label нужен?    --%>
            <label for="inputEmail" class="sr-only">Email address</label>
            <%--        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>--%>
            <input type="text" id="inputEmail" class="form-control" name="login" placeholder="Email address" required
                   autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Пароль"
                   required>

            <%--            <div class="checkbox mb-3">--%>
            <%--                <label>--%>
            <%--                    <input type="checkbox" value="remember-me">Remember me--%>
            <%--                </label>--%>
            <%--            </div>--%>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.sign_in_button"/> </button>
        </form>

        <a href="/registration"><fmt:message key="login.registration_button"/> </a>
    </div>

    <%@ include file="/page/footer.jsp" %>
</div>
</body>
</html>
