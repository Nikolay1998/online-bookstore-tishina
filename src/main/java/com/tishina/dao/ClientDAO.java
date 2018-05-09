package com.tishina.dao;

import com.tishina.model.Client;

public interface ClientDAO {
    Client getClientByLogin(String login);
    Client getClient(String login, String password);
    Client getClientById(Integer id);
    Client createClient(Client client);
}
