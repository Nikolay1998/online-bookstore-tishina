<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>

<%!
    //This is declaration part. ArrivalDAO will be get from DAOFactoryHolder and DAOFactory during first call of this page
    public static final ArrivalDAO arrivalDAO = DAOFactoryHolder.getDAOFactory().getArrivalDAO();
%>

<%
    Collection<Arrival> arrivals = arrivalDAO.getAllArrivals();
%>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Поиск по поступлениям</title>
	</head>
	<body bgcolor="#FAFAD2">

        <table cellspacing="2" border="1" cellpadding="5" width="600">
               <%
                    for (Arrival arrival : arrivals) {
                %>
                <tr>
                    <td><%=arrival.getUnique_names()%></td>
                    <td><%=arrival.getAmount()%></td>
                    <td><%=arrival.getA_date()%></td>
                </tr>
            <%
                   }
            %>
        </table>
	</body>
</html>