<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%--<html lang="${cookie['lang'].value}">--%>
<head>
    <title>Элетронная медицинская карта</title>
</head>

<body class="text-center">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
    <%@ include file="/page/header.jsp" %>

    <main role="main" class="inner cover">
        <h2>
            <%--            <fmt:message key="label.welcome"/>--%>
            <h1 class="cover-heading">Ваша электронная медицинская карта</h1>
            <p class="lead">Получайте информацию о состоянии своего здоровья без посещения поликлиники —
                медицинская карта теперь всегда под рукой!</p>
        </h2>
    </main>

    <%@ include file="/page/footer.jsp" %>
</div>
</body>
</html>
