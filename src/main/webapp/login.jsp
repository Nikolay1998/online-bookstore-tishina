<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>


<%!
    public static final ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();


%>

<%
    if (request.getParameter("login") == null) {
        throw new RuntimeException("Id is not specified!");
    }
    Integer login = Integer.valueOf(request.getParameter("login"));
    Client client = ClientDAO.getByLogin(login);
%>

<html>
<head>
	<meta charset="utf8">
	<title>Форма регистрации</title>
	<link rel="StyleSheet" href="css/login.css" type="text/css">

</head>
<body>



<div id="login_container">
	<div id="form_container">
		<p class="login-text">Авторизация на сайте</p>
		<form method='POST'>
		<input type='text' onFocus="if(this.value=='Логин')this.value=''" onblur="if(this.value=='')this.value='Логин'" name='username' value='Логин' class='text_input' />
		<input type='text' onFocus="if(this.value=='Пароль')this.value=''" onblur="if(this.value=='')this.value='Пароль'" name='password' value='Пароль' class='text_input' />
		<input type='submit' value='' id='login' name='login' />
		</form>
	</div>
</div>
</body>
</html>