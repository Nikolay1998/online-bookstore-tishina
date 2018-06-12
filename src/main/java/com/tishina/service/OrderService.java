package com.tishina.service;

import com.tishina.dao.BookDAO;
import com.tishina.dao.DAOFactoryHolder;
import com.tishina.dao.OrderDAO;
import com.tishina.integration.SendMailTLS;
import com.tishina.model.Book;
import com.tishina.model.Client;
import com.tishina.model.Order;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderDAO orderDAO = DAOFactoryHolder.getDAOFactory().getOrderDAO();

    /**
     * Create order for client with books. Created order is in status 'active'.
     * Method is transactional. It means that if at least one operation fails then all operations should be cancelled
     * 1. check availability of books on warehouse<br/>
     * 2. create order<br/>
     *   2.1 row in order table<br/>
     *   2.2 row in order_book per each book<br/>
     * 3. decrease amount of each book on warehouse<br/>
     * 4. send mail to client
     * @param client client for which order should be created. Maybe null - it means that anonimous order should be created
     * @param books list of book and its amounts which client ordered
     */
    public Integer submitNewOrder(Client client, List<Book> books){
        try {
            if (client == null) {
                throw new UnsupportedOperationException("Order creation is not supported for anonimus. Please login");
            }

            for (Book book : books) {
                if (book.getWhAmount() < book.getOrderedAmount()) {
                    throw new UnsupportedOperationException("There is no ordered amount of book in store");
                }
            }
            UserTransaction ut = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
            ut.begin();

            BookDAO bookDAO = DAOFactoryHolder.getDAOFactory().getBookDAO();
            for (Book book : books) {
                bookDAO.updateBook(book);
            }
            //if (true) throw new Exception("Some exception to check transactionality");
            Order order = new Order(client, books);
            order.setPrice(calculateOrderCost(order));
            Integer orderId = orderDAO.createOrder(order);

            SendMailTLS.sendMail(order);
            ut.commit();
            return orderId;
        } catch (Exception ex) {
            System.err.println(ex);
            throw new RuntimeException(ex);
        }
    }

    public Order getOrder(Integer id) {
        return orderDAO.getOrder(id);
    }

    public List<Order> getOrdersByClient(Integer clientId, boolean onlyIsActive) {
        List<Order> orders = orderDAO.getOrdersByClient(clientId);
        if (onlyIsActive) {
            Iterator<Order> it = orders.iterator();
            while (it.hasNext()) {
                Order order = it.next();
                if (!order.isActive()) {
                    it.remove();
                }
            }
        }
        return orders;
    }

    public double calculateOrderCost(Order order) {
        double orderPrice = 0;
        for (Map.Entry<Book, Integer> bookAndAmount : order.getBooks().entrySet()) {
            Book book = bookAndAmount.getKey();
            Double bookPrice = book.getPrice();
            if (bookPrice == null) {
                bookPrice = 0d;
            }
            orderPrice = orderPrice + bookPrice * book.getOrderedAmount();
        }
        return orderPrice;
    }
}
