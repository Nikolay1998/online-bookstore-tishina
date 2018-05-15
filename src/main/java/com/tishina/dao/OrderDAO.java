package com.tishina.dao;

import com.tishina.model.Order;

public interface OrderDAO {
    Order getOrder(Integer id);
    Order createOrder(Order order);
}
