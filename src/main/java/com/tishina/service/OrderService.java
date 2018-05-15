package com.tishina.service;

import com.tishina.dao.BookDAO;
import com.tishina.dao.DAOFactoryHolder;
import com.tishina.dao.OrderDAO;
import com.tishina.model.Book;
import com.tishina.model.Client;
import com.tishina.model.Order;

import java.util.List;

public class OrderService {
    OrderDAO orderDAO = DAOFactoryHolder.getDAOFactory().getOrderDAO();

    /**
     * 1. check availability of books on warehouse<br/>
     * 2. create order<br/>
     *   2.1 row in order table<br/>
     *   2.2 row in order_book per each book<br/>
     * 3. decrease amount of each book on warehouse<br/>
     * @param client client for which order should be created. Maybe null - it means that anonimous order should be created
     * @param books list of book and its amounts which client ordered
     */
    public Order submitNewOrder(Client client, List<Book> books){
        if (client == null) {
            throw new UnsupportedOperationException("Order creation is not supported for anonimus. Please login");
        }
        //todo: Need to be transactional - if one operations fails then all should be cancelled
        //todo: 1. need to check availability of books on warehouse

        BookDAO bookDAO = DAOFactoryHolder.getDAOFactory().getBookDAO();
        for (Book book : books) {
            bookDAO.updateBook(book);
        }

        Order order = new Order(client, books);
        return orderDAO.createOrder(order);
    }

    public Order getOrder(Integer id) {
        return orderDAO.getOrder(id);
    }
}
