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
	<link rel="StyleSheet" href="css/login.css" type="text/css">
</head>
<body>
<div id = "login_container">

    <div id = "form_container" >
        <%=("Login".equals(request.getParameter("Action")) ? "<font color=red>Login or password is incorrect!</font>" : "") %>
        <p class="login-text">Авторизация на сайте</p>
        <form name="form" action="login.jsp">
           <input type="text" name="login" onFocus="if(this.value=='Логин')this.value=''" onblur="if(this.value=='')this.value='Логин'" value='Логин'  class='text_input' />
           <input type="text" name="password" onFocus="if(this.value=='Пароль')this.value=''" onblur="if(this.value=='')this.value='Пароль'" value='Пароль' class='text_input' />
           <input type="submit" value="Login" id= 'login' name="Action" />
           <input type="submit" value="Signin" id='signin' name="Action" />
        </form>
    </div>
</div>
</body>
</html>