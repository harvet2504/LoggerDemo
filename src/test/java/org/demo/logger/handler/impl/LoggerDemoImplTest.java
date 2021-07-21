package org.demo.logger.handler.impl;

import org.demo.logger.exception.ConfigurationException;
import org.demo.logger.exception.NotFoundHandlerException;
import org.demo.logger.handler.console.impl.ConsoleLoggerImpl;
import org.demo.logger.handler.database.DatabaseLogger;
import org.demo.logger.handler.database.impl.DatabaseLoggerImpl;
import org.demo.logger.handler.file.impl.FileLoggerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoggerDemoImplTest {

    @Test
    void logMessageFile() throws ConfigurationException, SQLException, NotFoundHandlerException, IOException {
        LoggerDemoImpl loggerDemo = new LoggerDemoImpl(false, true, false, true, false, false, new DatabaseLoggerImpl(), new ConsoleLoggerImpl(), new FileLoggerImpl());
        loggerDemo.logMessage("message", true, false,false);
        assert true;
    }
}