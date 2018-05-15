<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.* " %>
<%@page import="com.tishina.model.*" %>
<jsp:useBean id="orderService" class="com.tishina.service.OrderService" scope="application" />

<head>
    <meta charset="UTF-8">
    <title>Магазин книг "Тишина"</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#CCCCCC">

<div id = "outer">
    <jsp:include page="menuFragment.jsp" />
    <jsp:include page="searchFragment.jsp" />

    <div id = "content" class = "basic">
        <c:set var = "id" scope = "session" value = "${param['id']}"/>
        <c:set var = "order" scope = "session" value = "${orderService.getOrder(id)}"/>
        <table cellspacing="2" border="1" cellpadding="5" width="600">
            <tr>
                <td>Номер заказа:</td>
                <td><c:out value="${order.getId()}"/></td>
            </tr>
            <tr>
                <td>Дата заказа:</td>
                <td><c:out value="${order.getStartDate()}"/></td>
            </tr>
            <tr>
                <td>Дата доставки:</td>
                <td><c:out value="${order.getCompletionDate()}"/></td>
            </tr>
            <tr>
                <td>Статус:</td>
                <td><c:out value="${order.getStatus()}"/></td>
            </tr>
            <tr>
                <td>Книги   :</td>
                <td>
                    <table>
                    <c:forEach var="bookAndAmount" items="${order.getBooks().entrySet()}">
                        <tr>
                            <td><c:out value = "${bookAndAmount.getKey().getName()}"/></td>
                            <td><c:out value = "${bookAndAmount.getValue()}"/></td>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
            </tr>
        </table>

    </div>
</div>
</body>
</html>