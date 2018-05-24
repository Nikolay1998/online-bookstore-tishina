<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.* " %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.integration.*" %>

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
        <c:set var = "report" scope = "session" value = "${integrationReport}"/>
        <H5>Добавленные авторы</H5>
        <table cellspacing="2" border="1" cellpadding="5" width="700">
            <c:forEach var="author" items="${report.getCreatedAuthors()}">
            <tr>
                <td><c:out value = "${author.getName()}"/></td>
                <td><c:out value = "${author.getDescription()}"/></td>
            </tr>
            </c:forEach>
        </table>
        <br/>

        <H5>Уже существующие в каталоге авторы</H5>
        <table cellspacing="2" border="1" cellpadding="5" width="700">
            <c:forEach var="author" items="${report.getExistedAuthors()}">
            <tr>
                <td><c:out value = "${author.getName()}"/></td>
                <td><c:out value = "${author.getDescription()}"/></td>
            </tr>
            </c:forEach>
        </table>
        <br/>

        <H5>Добавленные книги</H5>
        <table cellspacing="2" border="1" cellpadding="5" width="700">
          <thead>
              <tr>
                <td>Название</td>
                <td>Автор</td>
                <td>О книге</td>
                <td>Рейтинг</td>
                <td>Осталось на складе</td>
                <td>Цена</td>
              </tr>
          </thead>
          <tbody>
            <c:forEach var="book" items="${report.getCreatedBooks()}">
            <tr>
                <td><c:out value = "${book.getName()}"/></td>
                <td><c:out value = "${book.getAuthor().getName()}"/></td>
                <td><c:out value = "${book.getDescription()}"/></td>
                <td><c:out value = "${book.getRating()}"/></td>
                <td><c:out value = "${book.getWhAmount()}"/></td>
                <td><c:out value = "${book.getPrice()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <br/>

        <H5>Уже существующие в каталоге книги</H5>
        <table cellspacing="2" border="1" cellpadding="5" width="700">
          <thead>
              <tr>
                <td>Название</td>
                <td>Автор</td>
                <td>О книге</td>
                <td>Рейтинг</td>
                <td>Осталось на складе</td>
                <td>Цена</td>
              </tr>
          </thead>
          <tbody>
            <c:forEach var="book" items="${report.getExistedBooks()}">
            <tr>
                <td><c:out value = "${book.getName()}"/></td>
                <td><c:out value = "${book.getAuthor().getName()}"/></td>
                <td><c:out value = "${book.getDescription()}"/></td>
                <td><c:out value = "${book.getRating()}"/></td>
                <td><c:out value = "${book.getWhAmount()}"/></td>
                <td><c:out value = "${book.getPrice()}"/></td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <br/>

    </div>
</div>
</body>
</html>