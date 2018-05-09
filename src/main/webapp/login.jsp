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
    if ("Signin".equals(request.getParameter("Action"))) {
        response.sendRedirect("signin.jsp");
    }
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
	<link rel="StyleSheet" href="css/style.css" type="text/css">
</head>
<body>
<div id = "outer">
    <jsp:include page="menuFragment.jsp" />
    <jsp:include page="searchFragment.jsp" />

    <div id = "content" class = "basic">
        <%=("Login".equals(request.getParameter("Action")) ? "<font color=red>Login or password is incorrect!</font>" : "Введите логин и пароль") %>

        <form name="form" action="login.jsp">
            <table>
                <tr>
                    <td>Логин</td>
                    <td><input type="text" name="login" value="" size="30" /></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="text" name="password" value="" size="30" /></td>
                </tr>
            </table>
            <input type="submit" value="Login" name="Action" />
            <input type="submit" value="Signin" name="Action" />
        </form>
    </div>
</div>
</body>
</html>