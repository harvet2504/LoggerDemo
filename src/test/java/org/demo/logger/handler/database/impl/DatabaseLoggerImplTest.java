package org.demo.logger.handler.database.impl;

import org.demo.logger.handler.database.DatabaseLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLoggerImplTest {

    private DatabaseLoggerImpl databaseLogger;

    @BeforeEach
    void setUp() {
        databaseLogger = new DatabaseLoggerImpl();
    }

    @Test
    void runUpdate() throws SQLException {
        databaseLogger.runUpdate("insert into Log_Values VALUES('Testing message insertion in BD', 1)");
        assert true;
    }
}