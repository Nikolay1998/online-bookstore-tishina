<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.util.EnvironmentTester" %>
<%@page import="com.tishina.util.EnvironmentTester.EnvironmentTestResult" %>

<%!
    EnvironmentTester instance = EnvironmentTester.getInstance();
%>

<%
    EnvironmentTestResult javaResult = instance.testJavaMethod();
    EnvironmentTestResult dbConnectionResult = instance.testDBConnection();
%>

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Проверка окружения</title>
	</head>
	<body>
        <H1>Checking environment...</H1>
        <br/>Testing Java class: <font color=<%= (javaResult.isOk() ? "green" : "red") %> > <%=javaResult.message %> </font>
        <br/>Testing connection to DataBase: <font color=<%= (dbConnectionResult.isOk() ? "green" : "red") %> >
            <%=dbConnectionResult.message %> </font>
	</body>
</html>