<%-- This page represents page for search authors. --%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>

<%!
    //This is declaration part. AuthorDAO will be get from DAOFactoryHolder and DAOFactory during first call of this page
    public static final AuthorDAO authorDAO = DAOFactoryHolder.getDAOFactory().getAuthorDAO();
%>

<%
    Collection<Author> authors = null;
    if ("Search".equals(request.getParameter("Action")) &&
            request.getParameter("authorName") != null && !"".equals(request.getParameter("authorName"))) {
        authors = authorDAO.getAuthorsByName(request.getParameter("authorName"), false, false);
    } else {
        authors = authorDAO.getAllAuthors();
    }

%>

<html>
	<head>
        <meta charset="UTF-8">
            <title>Магазин книг "Тишина"</title>
            <link rel="stylesheet" href="css/style.css" type="text/css">
        </head>
	</head>
	<body>
	    <div id = "outer">
            <jsp:include page="menuFragment.jsp" />
            <jsp:include page="searchFragment.jsp" />

            <div id = "content" class = "basic">
                <form name="form" action="authors.jsp">
                    <input type="text" name="authorName" value="" size="30" />
                    <br/>
                    <input type="submit" value="Search" name="Action" />
                </form>

                <table cellspacing="2" border="1" cellpadding="5" width="600">
                <%
                    for (Author author : authors) {
                %>
                    <tr>
                        <td><%=new HtmlHref("author.jsp", author.getId(), author.getName()).print() %></td>
                        <td><%=author.getDescription() %></td>
                    </tr>
                <%
                    }
                %>
                </table>
            </div>
        </div>
	</body>
</html>