<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
    <%@ include file="/page/i18n.jsp" %>
    <title><fmt:message key="home.page.title"/></title>
</head>

<body class="text-center">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="/page/header.jsp" %>

    <main role="main" class="inner cover">
        <h2>
            <h1 class="cover-heading"><fmt:message key="home.text.title"/> </h1>
            <p class="lead"><fmt:message key="home.text"/> </p>
        </h2>
    </main>

    <%@ include file="/page/footer.jsp" %>
</div>
</body>
</html>
