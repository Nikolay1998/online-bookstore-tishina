<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.* " %>
<%@page import="com.tishina.model.*" %>
<jsp:useBean id="card" class="com.tishina.service.Card" scope="session" />

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
        <c:if test = "${card.isEmpty()}">
            <p>Ваша корзина пока пуста</p>
        </c:if>

        <c:if test = "${!card.isEmpty()}">
            <form action="./confirmOrder" method="POST">
                <table>
                    <c:forEach var="book" items="${card.getBooks()}">
                    <tr>
                        <td><c:out value = "${book.getName()}"/></td>
                        <td><c:out value = "${book.getOrderedAmount()}"/></td>
                    </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Оформить заказ"/>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>