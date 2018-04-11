package com.tishina.util;

import dao.TishinaDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnvironmentTester {
    private static final EnvironmentTester instance = new EnvironmentTester(); //Pattern "Singleton"
    private static final String mysqlTestConnectionQuery = "select 1 from dual";

    private EnvironmentTester(){
        //declaration of 'private' constructor to restrict instance creation from out of this class
    }

    public static EnvironmentTester getInstance(){
        return instance;
    }

    public EnvironmentTestResult testJavaMethod() {
        try {
            return new EnvironmentTestResult(EnvironmentTestResult.Status.OK, "Hello, web-world from java class!");
        } catch (Exception ex) {
            System.err.println(ex);
            return new EnvironmentTestResult(EnvironmentTestResult.Status.ERROR, ex.getMessage());
        }
    }

    public EnvironmentTestResult testDBConnection() {
        try {
            DataSource ds = TishinaDataSource.getDataSource();

            Connection conn = ds.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(mysqlTestConnectionQuery);

            if (rs.next() && 1 ==rs.getInt(1) ) {
                return new EnvironmentTestResult(EnvironmentTestResult.Status.OK,
                        "Hello world from database! Select 1 from database: "+rs.getInt(1));
            }
            return new EnvironmentTestResult(EnvironmentTestResult.Status.WARNING,
                    "No errors but empty result from Database");
        } catch (SQLException ex) {
            System.err.println(ex);
            return new EnvironmentTestResult(EnvironmentTestResult.Status.ERROR,
                    "Error during database connection: "+ ex.getMessage());
        }
    }

    public static class EnvironmentTestResult{
        public enum Status {
            ERROR, OK, WARNING
        }

        public String message;
        public Status status;

        public EnvironmentTestResult(Status status, String message) {
            this.message = message;
            this.status = status;
        }

        public boolean isOk(){
            return status == Status.OK;
        }
    }
}
