<%@page pageEncoding="UTF-8" %>
<%@page import="com.tishina.model.*" %>
<%
    Client client = (Client) session.getAttribute("client");
%>
<div id = "menu" class = "basic" align="center">
    <ul class = "menu">
        <li><a href="index.jsp"  class = "menu">Главная</a></li>
        <li><a href=""  class = "menu">Корзина</a></li>
        <li><a href="arrivals.jsp"  class = "menu">История поступлений</a></li>
    <% if (client == null) { %>
        <li><a href="login.jsp"  class = "menu">Войти</a></li>
    <% } else { %>
        <li><a href="client.jsp" class = "menu">Мой кабинет</a></li>
    <% } %>
    </ul>
</div>