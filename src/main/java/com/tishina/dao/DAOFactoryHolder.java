package com.tishina.dao;

import com.tishina.dao.impl.MysqlDAOFactory;

public class DAOFactoryHolder {
    //in future we may change implementation of DAOFactory from Mysql to another. This is single place which will be changed
    private static final DAOFactory instance = new MysqlDAOFactory();

    public static DAOFactory getDAOFactory(){
        return instance;
    }
}
