<link href="../styles/css/bootstrap.min.css" rel="stylesheet">
<link href="styles/cover.css" rel="stylesheet">

<header class="masthead mb-auto">
    <div class="inner">
        <h3 class="masthead-brand"><fmt:message key="header.text"/></h3>

        <%--???????--%>
        <c:set scope="session" value="${sessionScope}" var="sesseionScope"/>

        <nav class="nav nav-masthead justify-content-center">
            <c:if test="${sessionScope.user == null}">
                <a class="nav-link" href="/"><fmt:message key="header.button.home"/></a>
                <a class="nav-link" href="/login"><fmt:message key="header.button.login"/></a>
                <a class="nav-link" href="/registration"><fmt:message key="header.button.registration"/> </a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <a class="nav-link" href="/logout"><fmt:message key="header.button.logout"/></a>
            </c:if>
        </nav>

<%--        !!!!!--%>
        <div>
            <form>
                <select id="language" name="language" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                </select>
            </form>
        </div>

    </div>
</header>

