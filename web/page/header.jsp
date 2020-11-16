<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header class="masthead mb-auto">
    <div class="inner">
        <h3 class="masthead-brand">Hospital</h3>

        <c:set scope="session" value="${sessionScope}" var="sesseionScope"/>

        <nav class="nav nav-masthead justify-content-center">
            <c:if test="${sessionScope.user == null}">
                <a class="nav-link" href="/">Home</a>
                <a class="nav-link" href="/login">Login</a>
                <a class="nav-link" href="/registration">Registration</a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a class="nav-link" href="/logout">Logout</a>
            </c:if>
        </nav>

    </div>
</header>

