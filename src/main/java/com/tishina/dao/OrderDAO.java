package com.tishina.dao;

import com.tishina.model.Order;

import java.util.List;

public interface OrderDAO {
    Order getOrder(Integer id);
    List<Order> getOrdersByClient(Integer clientId);
    Integer createOrder(Order order);
}
