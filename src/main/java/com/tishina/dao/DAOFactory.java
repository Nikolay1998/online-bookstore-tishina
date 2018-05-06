package com.tishina.dao;

public interface DAOFactory {

    public AuthorDAO getAuthorDAO();
    public BookDAO getBookDAO();
    public ArrivalDAO getArrivalDAO();
    public ClientDAO getClientDAO();
}
