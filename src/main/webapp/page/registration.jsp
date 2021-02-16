<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="i18n.jsp" %>
    <title><fmt:message key="registration.page.title"/></title>

    <style>
        .registration-form {
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

<c:set scope="request" value="${doctorTypeId}" var="doctorTypeId"/>
<c:set scope="request" value="${nurseTypeId}" var="nurseTypeId"/>

<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="/page/header.jsp" %>

    <div class="registration-form">
        <form class="form-signin" action="registration" method="post">

            <%--    !!!!!!!!!!!--%>
            <div class="exception">
                <c:set scope="request" value="${errorMessage}" var="error"/>
                <c:out value="${error}"/>
            </div>

            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="registration.form.title"/></h1>

            <input type="text" class="form-control" required name="name" placeholder="Name"/>
            <input type="text" class="form-control" required name="surname" placeholder="Surname"/>
            <input type="email" class="form-control"  name="login" placeholder="Email address" required autofocus>

            <br>

            <p><fmt:message key="registration.message.requirements.password"/> </p>
            <input type="password" class="form-control" minlength="8" required name="password" placeholder="Password"/>

            <br>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" requred name="doctorTypeId" value="${doctorTypeId}"><fmt:message key="registration.doctor"/></input>
                </label>
                <label>
                    <input type="checkbox" requred name="nurseTypeId" value="${nurseTypeId}"><fmt:message key="registration.nurse"/> </input>
                </label>
            </div>

            <input class="btn btn-lg btn-primary btn-block" type="submit" value=<fmt:message key="registration.button.register"/> />
        </form>

        <a href="/login"><fmt:message key="registration.link.login"/></a>
    </div>

    <%@ include file="/page/footer.jsp" %>

</div>
</body>
</html>
