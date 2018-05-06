package com.tishina.dao;

import com.tishina.model.Client;

public interface ClientDAO {
    public Boolean isLogin(String login, String password);
    public Client getClient(String login, String password);
}
