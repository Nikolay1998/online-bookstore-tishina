package com.tishina.dao.impl;

import com.tishina.dao.ArrivalDAO;
import com.tishina.dao.AuthorDAO;
import com.tishina.dao.BookDAO;
import com.tishina.dao.DAOFactory;
import com.tishina.dao.impl.AuthorMysqlDAO;

import java.util.HashMap;

public class MysqlDAOFactory implements DAOFactory {
    private final static HashMap<Class, Object> daos = new HashMap<>();
    static {
        daos.put(AuthorDAO.class, new AuthorMysqlDAO());
        daos.put(BookDAO.class, new BookMysqlDAO());
        daos.put(ArrivalDAO.class, new ArrivalMysqlDAO());
    }

    public AuthorDAO getAuthorDAO(){
        return (AuthorDAO) daos.get(AuthorDAO.class);
    }
    public BookDAO getBookDAO(){
        return (BookDAO) daos.get(BookDAO.class);
    }
    public ArrivalDAO getArrivalDAO(){
        return (ArrivalDAO) daos.get(ArrivalDAO.class);
    }
}
