<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>

<%!
    public static final BookDAO bookDAO = DAOFactoryHolder.getDAOFactory().getBookDAO();
%>

<%
    if (request.getParameter("id") == null) {
        throw new RuntimeException("Book id is not specified!");
    }
    Integer id = Integer.valueOf(request.getParameter("id"));
    Book book = bookDAO.getById(id);
%>
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
                <form action="./addBookToCard" method="POST">
                    <table cellspacing="2" border="1" cellpadding="5" width="600">
                        <tr>
                            <td>Книга</td>
                            <td><%=book.getName()%></td>
                        </tr>
                        <tr>
                            <td>О книге</td>
                            <td><%=book.getDescription()%></td>
                        </tr>
                        <tr>
                            <td>Автор</td>
                            <td><%=new HtmlHref("author.jsp", book.getAuthor().getId(), book.getAuthor().getName()).print()%></td>
                        </tr>
                        <tr>
                            <td>Категория</td>
                            <td><%=new HtmlHref("category.jsp", book.getCategory().getId(), book.getCategory().getName()).print()%></td>
                        </tr>
                        <tr>
                            <td>Рейтинг</td>
                            <td>TODO: </td>
                        </tr>
                        <tr>
                            <td>Цена</td>
                            <td><%=book.getPrice()%></td>
                        </tr>
                        <tr>
                            <td>Осталось на складе</td>
                            <td><%=book.getWhAmount()%></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id" value=<%=book.getId()%> />
                    <input type="submit" value="Добавить в корзину"/>
                </form>
            </div>
        </div>

	</body>
</html>