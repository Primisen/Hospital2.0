<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        .patient-form {
            color: #000000;
            margin-bottom: 20px;
        }

        h4, h3, p {
            text-shadow: none;
        }
    </style>
</head>

<body>
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">

    <%@ include file="header.jsp" %>

    <h1>Ваши пациенты, ожидающие приема</h1>

    <c:forEach items="${patients}" var="patient">

        <form class="patient-form" action="reception" method="post">
            <div class="card mb-4 shadow-sm patient-form">

                <div class="card-header">
                    <h4><c:out value="${patient.surname} ${patient.name}"></c:out></h4>
                </div>

                <div class="card-body">
                    <p>Диагноз:</p>
                    <input type="text" name="diagnosis" placeholder="Введите диагноз">

                    <p>Тип лечения:</p>
                    <select name="treatmentTypeValue">
                        <c:forEach items="${treatmentType}" var="treatmentType">
                            <option value="${treatmentType}">${treatmentType.russianName}</option>
                        </c:forEach>
                    </select>

                    <p>Количество процедур:</p>
                    <input type="text" name="numberOfProcedures" placeholder="Введите количество процедур">

                    <br>
                    <input type="hidden" name="patientId" value="${patient.id}">
                    <input type="submit" name="ok" value="Подтвердить">
                </div>
            </div>
        </form>
    </c:forEach>

    <c:if test="${patients == null}">
        <p>У Вас нет пациентов ожидающих прием.</p>
    </c:if>

    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
