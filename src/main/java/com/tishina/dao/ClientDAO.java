package com.tishina.dao;

import com.tishina.model.Client;

public interface ClientDAO {
    public Client getByLogin(Integer id);
}
