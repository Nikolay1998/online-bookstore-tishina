<%-- This is author.jsp to show info about author, edit it and delete --%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>

<%!
    //This is declaration part. AuthorDAO will be get from DAOFactoryHolder and DAOFactory during first call of this page
    public static final AuthorDAO authorDAO = DAOFactoryHolder.getDAOFactory().getAuthorDAO();
%>

<%
    if (request.getParameter("id") == null) {
        throw new RuntimeException("Id is not specified!");
    }
    Integer id = Integer.valueOf(request.getParameter("id"));
    Author author = authorDAO.getById(id);
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
                        <td>Автор</td>
                        <td><%=author.getName()%></td>
                    </tr>
                    <tr>
                        <td>Об авторе</td>
                        <td><%=author.getDescription()%></td>
                    </tr>
                    <tr>
                        <td>Фото</td>
                        <td>TODO: улучшить базу, чтобы сохранять фото</td>
                    </tr>
                    <tr>
                        <td>Книги</td>
                        <td>
                        <%
                            for (Book book : author.getBooks()) {
                                HtmlHref href = new HtmlHref("book.jsp", book.getId(), book.getName());
                                out.write(href.print());
                            }
                        %>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
	</body>
</html>