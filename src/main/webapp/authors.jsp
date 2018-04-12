<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>

<%!
    public static final AuthorDAO authorDAO = DAOFactoryHolder.getDAOFactory().getAuthorDAO();
%>

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Поиск по авторам</title>
	</head>
	<body bgcolor="#FAFAD2">
        <table cellspacing="2" border="1" cellpadding="5" width="600">
        <%
            for (Author author : authorDAO.getAllAuthors()) {
        %>
            <tr>
                <td><a href="/author.jsp?id=<%=author.getId() %>"><%=author.getName()%></a></td>
                <td><%=author.getDescription() %></td>
            </tr>
        <%
            }
        %>
        </table>
	</body>
</html>