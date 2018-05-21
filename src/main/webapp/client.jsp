<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <table>
            <c:forEach var="order" items="${orderService.getOrdersByClient(id, true)}">
            <tr>
                <td><c:out value = "${order.getId()}"/></td>
                <td><c:out value = "${order.getStatus()}"/></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>