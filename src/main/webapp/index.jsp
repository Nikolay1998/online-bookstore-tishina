<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>

<head>
    <meta charset="UTF-8">
    <title>Магазин книг "Тишина"</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body bgcolor="#CCCCCC">

<div id = "outer">
    <div id = "menu" class = "basic" align="center">
        <ul class = "menu">
            <li><a href=""  class = "menu">Главная</a></li>
            <li><a href=""  class = "menu">Мой кабинет</a></li>
            <li><a href=""  class = "menu">Корзина</a></li>
            <li><a href=""  class = "menu">Войти</a></li>
        </ul>
    </div>
    <div id = "search" class = "basic">
        <input type="search" value = "Название книги"><br/><input type="submit" value="Найти"><br/>
        <a href="" class = "sections">По всем</a><br><br>
        <a href="" class = "sections">Учебная литература</a><br><br>
        <a href="" class = "sections">Детям и родителям</a><br><br>
        <a href="" class = "sections">Бизнес-литература</a><br><br>
        <a href="" class = "sections">Художественная литература</a><br><br>
    </div>
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