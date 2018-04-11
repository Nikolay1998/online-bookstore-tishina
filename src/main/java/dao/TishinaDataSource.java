package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This class is wrapper above DataSource and its JNDI name. It provides more transparent access to DataSource.
 * In future this class may be extended to work with different DataSources/
 */
public class TishinaDataSource {
    //todo: does it make sense to implement possibility to read this from any external file? For example, env.properties
    private final static String jndiDataSourceName = "jdbc/MysqlPool";

    public static DataSource getDataSource() {
        DataSource ds;
        try {
            Context initContext = new InitialContext();
            ds = (DataSource) initContext.lookup(jndiDataSourceName);
        } catch (NamingException ex) {
            ex.printStackTrace(System.err);
            throw new RuntimeException("Error during database connection: " + ex.getMessage());
        }
        return ds;
    }

}
