<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>


<%!
    public static final ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
%>

<%
    Client client = null;
    if ("Login".equals(request.getParameter("Action"))) {
        client = clientDAO.getClient(request.getParameter("login"), request.getParameter("password"));
        if (client != null) {
            session.setAttribute("client", client);
        }
    }

    if (session.getAttribute("client") != null) {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>

<html>
<head>
	<meta charset="utf8">
	<title>Войти в кабинет</title>
	<link rel="StyleSheet" href="css/login.css" type="text/css">
</head>
<body>
    <%=("Login".equals(request.getParameter("Action")) ? "Login or password is incorrect!" : "") %>

    <form name="form" action="login.jsp">
        <input type="text" name="login" value="" size="30" />
        <br/>
        <input type="text" name="password" value="" size="30" />
        <br/>
        <input type="submit" value="Login" name="Action" />
    </form>
</body>
</html>