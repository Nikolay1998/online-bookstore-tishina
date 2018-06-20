package com.tishina.servlets;

import com.tishina.integration.ImportReport;
import com.tishina.integration.IntegrationService;
import com.tishina.model.Client;
import com.tishina.service.Card;
import com.tishina.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UpdateBookCatalogServlet", urlPatterns = {"/updateBookCatalog"})
public class UpdateBookCatalogServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("C:\\Users\\User\\IdeaProjects\\Online-store\\src\\main\\webapp\\import_test.txt");

        IntegrationService service = new IntegrationService();
        ImportReport report = service.updateBookCatalog(file);
        System.out.println(report);
        request.getSession().setAttribute("integrationReport", report);
        response.sendRedirect("./integrationsReport.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "GET requests are not supported");
    }
}
