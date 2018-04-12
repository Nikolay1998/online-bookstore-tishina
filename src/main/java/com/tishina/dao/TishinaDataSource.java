package com.tishina.dao;

import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * This class is wrapper above DataSource and its JNDI name. It provides more transparent access to DataSource.
 * This class is singleton because there is need to create more than one object to access to one DataSource object.
 * In future this class may be extended to work with different DataSources.
 */
public class TishinaDataSource {
    //todo: does it make sense to implement possibility to read this from any external file? For example, env.properties
    private final static String jndiDataSourceName = "jdbc/MysqlPool";
    private final static TishinaDataSource instance = new TishinaDataSource();
    private final DataSource dataSource;

    private TishinaDataSource() {
        dataSource = initDataSource();
    }

    private static DataSource initDataSource() {
        try {
            Context initContext = new InitialContext();
            return (DataSource) initContext.lookup(jndiDataSourceName);
        } catch (NamingException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to get DataSource from JNDI: " + ex.getMessage());
        }
    }

    public static DataSource getDataSource() {
        return instance.dataSource;
    }

    public static Object executePreparedStatement(String sqlQuery, ResultSetHandler<Collection<Author>> handler) {
        try {
            Connection connection = instance.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            return handler.handle(statement.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to execute database query: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException(ex);
        }
    }

}
