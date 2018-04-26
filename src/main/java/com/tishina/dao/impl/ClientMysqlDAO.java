package com.tishina.dao.impl;

import com.tishina.dao.ClientDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Client;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientMysqlDAO implements ClientDAO {
    private static final String getClientByLoginQuery = "select id, name, password where login = ?";

    @Override
    public Client getByLogin(Integer login) {
        return (Client) TishinaDataSource.executePreparedStatement(
                getClientByLoginQuery,
                new Object[][]{{JDBCType.INTEGER, login}},
                new ResultSetHandler<Client>(){
                    public Client handle(ResultSet rs) throws SQLException {
                        if (!rs.next()) {
                            throw new RuntimeException("Can't find client with login = "+login);
                        }
                        Client client = null;

                        client = new Client(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"),
                                rs.getString("password"));

                        return client;
                    }
                }
        );
    }
}
