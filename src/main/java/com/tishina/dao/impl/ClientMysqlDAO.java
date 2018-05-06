package com.tishina.dao.impl;

import com.tishina.dao.ClientDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Client;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientMysqlDAO implements ClientDAO {
    private static final String getClientByLoginAndPasswordQuery = "select id, name, login from Client where login = ? and password = ?";

    @Override
    public Boolean isLogin(String login, String password) {
        return (Boolean) TishinaDataSource.executePreparedStatement(
                getClientByLoginAndPasswordQuery,
                new Object[][]{{JDBCType.VARCHAR, login}, {JDBCType.VARCHAR, password}},
                new ResultSetHandler() {
                    @Override
                    public Boolean handle(ResultSet rs) throws Exception {
                        return rs.next();
                    }
                }
        );
    }

    @Override
    public Client getClient(String login, String password) {
        return (Client) TishinaDataSource.executePreparedStatement(
                getClientByLoginAndPasswordQuery,
                new Object[][]{{JDBCType.VARCHAR, login}, {JDBCType.VARCHAR, password}},
                new ResultSetHandler() {
                    @Override
                    public Client handle(ResultSet rs) throws Exception {
                        if (!rs.next()) {
                            return null;
                        }
                        Client client = new Client(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"));
                        return client;
                    }
                }
        );
    }
}
