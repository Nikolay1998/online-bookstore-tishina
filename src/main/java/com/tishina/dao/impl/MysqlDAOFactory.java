package com.tishina.dao.impl;

import com.tishina.dao.AuthorDAO;
import com.tishina.dao.DAOFactory;
import com.tishina.dao.impl.AuthorMysqlDAO;

import java.util.HashMap;

public class MysqlDAOFactory implements DAOFactory {
    final static HashMap<Class, Object> daos = new HashMap<>();
    static {
        daos.put(AuthorDAO.class, new AuthorMysqlDAO());
    }

    public AuthorDAO getAuthorDAO(){
        return (AuthorDAO) daos.get(AuthorDAO.class);
    }
}
