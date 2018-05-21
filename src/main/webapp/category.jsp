<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.service.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>
<jsp:useBean id="bookService" class="com.tishina.service.BookService" scope="application" />

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Магазин книг "Тишина"</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
	</head>
	<body bgcolor="#FAFAD2">

	    <div id = "outer">
	        <jsp:include page="menuFragment.jsp" />
            <jsp:include page="searchFragment.jsp" />

            <div id = "content" class = "basic">
                <table cellspacing="2" border="1" cellpadding="5" width="800">
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
                    <c:set var = "id" scope = "session" value = "${param['id']}"/>
                    <c:forEach var="book" items="${bookService.findBooksByCategoryId(categoryId)}">
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
            </div>
        </div>

	</body>
</html>