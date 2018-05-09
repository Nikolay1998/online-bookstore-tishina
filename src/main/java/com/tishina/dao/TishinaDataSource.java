package com.tishina.dao;

import com.tishina.dao.util.ResultSetHandler;
import com.tishina.model.Author;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
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

    /**
     *
     * @param sqlQuery string that represents SQL query
     * @param params 2-dimensions array that represents array of pairs {JDBCType type, Object value}.<br/>
     *               For example: { {JDBCType.INTEGER, 100500}, {JDBCTYpe.VARCHAR, "Hello world"} };
     *               If sql query doesn't need parameters use null
     * @param handler instance of ResultSetHandler interface. Instance should be able to correctly retrieve values
     *                from ResultSet and transform them into instance of @com.tihina.model.* classes
     * @return object that represents result of executing query. In most cases it will be some Collection of model
     * classes. For example: Collection<Author>, Collection<Book>, Book, Integer and so forth
     */
    public static Object executePreparedStatement(String sqlQuery, Object[][] params, ResultSetHandler handler) {
        try {
            Connection connection = instance.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if (params != null) {
                setParametersToStatement(statement, params);
            }
            return handler.handle(statement.executeQuery());
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to execute database query: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException(ex);
        }
    }

    private static void setParametersToStatement(PreparedStatement statement, Object[][] params) throws SQLException {
        int i=0; //counter which points to parameter index. Starting from 1 - this is why we use prefix increment
        for (Object[] parameter : params) {

            if (JDBCType.INTEGER.equals(parameter[0])) {
                if (parameter[1] == null) {
                    statement.setNull(++i, Types.INTEGER);
                } else {
                    statement.setInt(++i, (Integer) parameter[1]);
                }
            } else if (JDBCType.VARCHAR.equals(parameter[0]) || JDBCType.DATE.equals(parameter[0])) {
                statement.setString(++i, (String) parameter[1]);
            }
//            else if (JDBCType.DATE.equals(parameter[0])) {
//                statement.setDate(++i, (java.sql.Date) parameter[1]);
//            }
        }
    }


    /**
     *
     * @param sqlQuery create/update/delete statement
     * @param params 2-dimensions array that represents array of pairs {JDBCType type, Object value}.<br/>
     *               For example: { {JDBCType.INTEGER, 100500}, {JDBCTYpe.VARCHAR, "Hello world"} };
     *               If sql query doesn't need parameters use null
     * @return count of updated rows
     */
    public static int executeDMLStatement(String sqlQuery, Object[][] params) {
        try {
            Connection connection = instance.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            if (params != null) {
                setParametersToStatement(statement, params);
            }
            return statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during attempt to execute database query: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException(ex);
        }
    }
}
