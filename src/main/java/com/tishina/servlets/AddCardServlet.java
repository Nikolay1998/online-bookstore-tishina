package com.tishina.servlets;

import com.tishina.service.Card;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCardServlet", urlPatterns = {"/addBookToCard"})
public class AddCardServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String value = request.getParameter("id");
        if (value == null || "".equals(value)) return; //todo: throw exception?
        Integer bookId = Integer.parseInt(value.trim());

        Card card = Card.getCard(request.getSession());
        card.addBook(bookId, 1); //todo: is it possible to add book in amount more than one??

        response.sendRedirect("./book.jsp?id="+bookId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "GET requests are not supported");
    }

}
