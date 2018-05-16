package com.tishina.dao;

import com.tishina.model.Order;

public interface OrderDAO {
    Order getOrder(Integer id);
    Integer createOrder(Order order);
}
