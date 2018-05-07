package com.tishina.dao;

import com.tishina.dao.impl.MysqlDAOFactory;

/**
 * This class encapsulates logic to choose implementation of DAOFactory.
  */
public class DAOFactoryHolder {
    //In future we may change implementation of DAOFactory from Mysql to another. This is single place which will be changed
    //Now it is hard-coded MysqlDAOFactory implementation
    private static final DAOFactory instance = new MysqlDAOFactory();

    public static DAOFactory getDAOFactory(){
        return instance;
    }
}
