<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="login.page.title"/></title>

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

    <%@ include file="header.jsp" %>

    <div class="login-form">

        <%--        !!!!!--%>
        <div class="exception">
            <c:set scope="request" value="${exceptionMessage}" var="exceptionMessage"/>
            <c:out value="${exceptionMessage}"/>
        </div>

        <form class="form-signin" action="login" method="post">

            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.form.title"/></h1>

            <input type="text" id="inputEmail" class="form-control" name="login" placeholder="Email address" required
                   autofocus>
            <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password"
                   required>

            <%--            <div class="checkbox mb-3">--%>
            <%--                <label>--%>
            <%--                    <input type="checkbox" value="remember-me">Remember me--%>
            <%--                </label>--%>
            <%--            </div>--%>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.button.sign.in"/></button>
        </form>

        <a href="/registration"><fmt:message key="login.button.registration"/> </a>
    </div>

    <%@ include file="/page/footer.jsp" %>
</div>
</body>
</html>
