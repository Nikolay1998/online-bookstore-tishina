package com.tishina.dao.impl;

import com.tishina.dao.ClientDAO;
import com.tishina.dao.TishinaDataSource;
import com.tishina.dao.util.IdGenerator;
import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Client;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientMysqlDAO implements ClientDAO {
    private static final String getClientByLoginAndPasswordQuery = "select id, name, login from Client where login = ? and password = ?";
    private static final String getClientByIdQuery = "select id, name, login from Client where id = ?";
    private static final String GET_CLIENT_BY_LOGIN = "select id, name, login from Client where login = ?";
    private static final String CREATE_CLIENT_QUERY = "insert into client(id, name, login, password, parent) values (?, ?, ?, ?, ?)";

    @Override
    public Client getClientByLogin(String login) {
        return (Client) TishinaDataSource.executePreparedStatement(
                GET_CLIENT_BY_LOGIN,
                new Object[][]{{JDBCType.VARCHAR, login}},
                new ResultSetHandler() {
                    @Override
                    public Object handle(ResultSet rs) throws Exception {
                        if (!rs.next()) {
                            return null;
                        }
                        return new Client(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("login"));
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

    @Override
    public Client getClientById(Integer id) {
        return (Client) TishinaDataSource.executePreparedStatement(
                getClientByIdQuery,
                new Object[][]{{JDBCType.INTEGER, id}},
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

    @Override
    public Client createClient(Client client) {
        int createdClientCount = TishinaDataSource.executeDMLStatement(
                CREATE_CLIENT_QUERY,
                new Object[][]{
                        {JDBCType.INTEGER, IdGenerator.getIdGenerator().getNewId()},
                        {JDBCType.VARCHAR, client.getName()},
                        {JDBCType.VARCHAR, client.getLogin()},
                        {JDBCType.VARCHAR, client.getPassword()},
                        {JDBCType.INTEGER, client.getReferral() == null ? null : client.getReferral().getId()}
                }
        );
        if (createdClientCount == 0) {
            System.out.println("Some error during client registration: "+client.toString());
            return null;
        } else {
            return client;
        }
    }
}
