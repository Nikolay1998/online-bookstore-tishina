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
        <title>Магазин книг "Тишина"</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
	</head>
	<body bgcolor="#FAFAD2">

	    <div id = "outer">
	        <jsp:include page="menuFragment.jsp" />
            <jsp:include page="searchFragment.jsp" />

            <div id = "content" class = "basic">
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
            </div>
        </div>

	</body>
</html>