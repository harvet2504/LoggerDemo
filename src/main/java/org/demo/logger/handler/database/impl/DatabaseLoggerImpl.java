package org.demo.logger.handler.database.impl;

import org.demo.logger.config.DefaultConfiguration;
import org.demo.logger.handler.database.DatabaseLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.StreamHandler;

public class DatabaseLoggerImpl extends StreamHandler implements DatabaseLogger {
    private Connection connection;
    private Statement statement;

    public void runUpdate(String updateQuery) throws SQLException {
        try {
            var configuration = new DefaultConfiguration();
            Class.forName(configuration.getProperty("logger.bd.jdbcDriver"));
            String userName = configuration.getProperty("logger.bd.userName");
            String password = configuration.getProperty("logger.bd.password");
            connection = DriverManager.getConnection(configuration.getProperty("logger.bd.dbUrl"), userName, password);
            statement = connection.createStatement();
            /*
            create table Log_Values (message varchar, type int)
             */
            statement.executeUpdate("create table IF NOT EXISTS Log_Values (message varchar, type int)");
            statement.executeUpdate(updateQuery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }
    }
}
