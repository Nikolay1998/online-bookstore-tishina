<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.model.*" %>

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
        Добро пожаловать на наш интернет-магазин книг!<br>
        <h2>Новинки:</h2>
        <div class="book">
            <a href="">
                <div class="img">
                    <img src="book.jpg">
                </div>
                <div class="text">Книга</div>
            </a>
        </div>
    </div>
</div>
</body>
</html>