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
        throw new RuntimeException("Id is not specified!");
    }
    Integer id = Integer.valueOf(request.getParameter("id"));
    Book book = bookDAO.getById(id);
%>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Поиск по книгам</title>
	</head>
	<body bgcolor="#FAFAD2">

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
                <td><%=book.getAuthor().getName()%></td>
            </tr>
            <tr>
                <td>Осталось на складе</td>
                <td><%=book.getAmount()%></td>
            </tr>
        </table>
	</body>
</html>