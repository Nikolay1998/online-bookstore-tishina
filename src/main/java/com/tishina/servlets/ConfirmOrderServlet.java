package com.tishina.servlets;

import com.tishina.service.Card;
import com.tishina.model.Client;
import com.tishina.model.Order;
import com.tishina.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ConfirmOrderServlet", urlPatterns = {"/confirmOrder"})
public class ConfirmOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        Card card = Card.getCard(session);

        //todo: how can we get this bean in servlet??
        OrderService orderService = new OrderService();
        Order order = orderService.submitNewOrder(client, card.getBooks());

        response.sendRedirect("./order.jsp?id="+order.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "GET requests are not supported");
    }

}
