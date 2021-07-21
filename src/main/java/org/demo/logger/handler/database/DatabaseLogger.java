package org.demo.logger.handler.database;


import java.sql.SQLException;

public interface DatabaseLogger {
    void runUpdate(String updateQuery) throws SQLException;
}
