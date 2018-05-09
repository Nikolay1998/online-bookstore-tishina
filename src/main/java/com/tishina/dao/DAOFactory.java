package com.tishina.dao;

public interface DAOFactory {

    AuthorDAO getAuthorDAO();
    BookDAO getBookDAO();
    ArrivalDAO getArrivalDAO();
    ClientDAO getClientDAO();
}
