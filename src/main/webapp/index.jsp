<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Книженции</title>

		 <style type="text/css">
           .block1 {
            width: 200px;
            height: 140px;
            background: #FFFFFF;
            padding: 10px;
            padding-right: 20px;
            border: solid 1px black;
            float: left;
            align: center;
           }
           .block2 {
            width: 200px;
            background: #fc0;
            padding: 5px;
            border: solid 1px black;
            float: left;
            position: relative;
            top: 40px;
            left: -70px;
           }
          </style>

	</head>
	<body bgcolor="#FAFAD2">
        <H1>Test page</H1>
        <table>
            <tr>
                <td class="block1" onclick="alert('clicked!')">
                    <div>
                    Книга 1
                    <img src="/classes/img/Book1.jpg" alt="альтернативный текст">
                    </div>
                </td>
                <td><div class="block1"><a href="/books.jsp">Книга 2</a>
                <img src="/img/Book1.jpg" alt="альтернативный текст">
                </div></td>

                <td><div class="block1"><a href="/books.jsp">Книга 3</a></div></td>
                <td><div class="block1"><a href="/books.jsp">Книга 4</a></div></td>
                <td><div class="block1"><a href="/books.jsp">Книга 5</a></div></td>
                <td><div class="block1"><a href="/books.jsp">Книга 6</a></div></td>
                <td><div class="block1"><a href="/books.jsp">Книга 7</a></div></td>
                <td><div class="block1"><a href="/books.jsp">Книга 8</a></div></td>
            </tr>
        </table>
	</body>
</html>