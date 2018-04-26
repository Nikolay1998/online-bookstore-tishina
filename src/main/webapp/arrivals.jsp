<%-- This page represents page for view arrivals. --%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.* " %>
<%@page import="com.tishina.dao.*" %>
<%@page import="com.tishina.model.*" %>
<%@page import="com.tishina.html.*" %>

<%!
    //This is declaration part. ArrivalDAO will be get from DAOFactoryHolder and DAOFactory during first call of this page
    public static final ArrivalDAO arrivalDAO = DAOFactoryHolder.getDAOFactory().getArrivalDAO();
%>

<%
    Collection<Arrival> arrivals = null;
       if ("Show".equals(request.getParameter("Action")) &&
                    request.getParameter("from") != null &&
                     request.getParameter("to") != null &&
                     !"".equals(request.getParameter("from"))
                     && !"".equals(request.getParameter("to"))) {
        arrivals = arrivalDAO.getArrivalsByDate(request.getParameter("from"), request.getParameter("to"));
    } else {
        arrivals = arrivalDAO.getAllArrivals();
    }

%>

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Поступления</title>
	</head>
	<body bgcolor="#FAFAD2">
	    <form name="form" action="arrivals.jsp">
            <input type="text" name="from" value="" size="30" />
            <br/>
            <input type="text" name="to" value="" size="30" />
            <br/>
            <input type="submit" value="Show" name="Action" />
        </form>

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