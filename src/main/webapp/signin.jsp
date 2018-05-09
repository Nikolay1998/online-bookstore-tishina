<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>
<%!
    public static final ClientDAO clientDAO = DAOFactoryHolder.getDAOFactory().getClientDAO();
%>

<%
    boolean loginAlreadyIsInUse = false;
    if ("Регистрация".equals(request.getParameter("Action"))) {
        //todo: сделать чтобы можно было создавать по реферальной ссылке
        if (clientDAO.getClientByLogin(request.getParameter("login")) != null) {
            loginAlreadyIsInUse = true;
        } else {
            Client client = new Client(request.getParameter("name"), request.getParameter("login"), request.getParameter("password"), null);
            Client createdClient = clientDAO.createClient(client);
            if (createdClient != null) {
                response.sendRedirect("login.jsp?Action=Login&login="+request.getParameter("login")+"&password="+request.getParameter("password"));
            }
        }
    }
%>
<head>
    <meta charset="UTF-8">
    <title>Магазин книг "Тишина"</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#CCCCCC">


<div id = "outer">
    <jsp:include page="menuFragment.jsp" />
    <jsp:include page="searchFragment.jsp" />

    <div id = "content" class = "basic">
        Добро пожаловать на наш интернет-магазин книг!<br/>
        <%=loginAlreadyIsInUse ? "<font color=red>Введенный login уже занят! Выберите другой</font>" : "Пожалуйста введите свои данные" %>
        <br/>

        <form name="form" action="signin.jsp">
            <input type="hidden" name="parentId" value=<%=request.getParameter("parentId")%> size="30" /><br/>
            <table>
                <tr>
                    <td>Имя</td>
                    <td><input type="text" name="name" value="" size="30" /></td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td><input type="text" name="login" value="" size="30" /></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="text" name="password" value="" size="30" /></td>
                </tr>
            </table>
            <input type="submit" value="Регистрация" name="Action" />
        </form>
    </div>
</div>
</body>
</html>