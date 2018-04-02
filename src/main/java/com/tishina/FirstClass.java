package com.tishina;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstClass {

    public String testMethod() {
        return "Hello, web-world from java class!";
    }

    public String testDBConnection() {
        try {
            Context initContext = new InitialContext();
            //Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) initContext.lookup("jdbc/MysqlPool");
            Connection conn = ds.getConnection();

            Statement statement = conn.createStatement();
            String sql = "select 1 from dual";
            ResultSet rs = statement.executeQuery(sql);

            int count = 1;
            while (rs.next()) {
                return "Hello world from database!";

            }
        } catch (NamingException | SQLException ex) {
            System.err.println(ex);
            return "Error during database connection";
        }
        return "empty";
    }
}
