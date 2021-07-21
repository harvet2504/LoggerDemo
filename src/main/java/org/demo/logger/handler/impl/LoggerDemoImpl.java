package org.demo.logger.handler.impl;

import org.demo.logger.exception.ConfigurationException;
import org.demo.logger.exception.NotFoundHandlerException;
import org.demo.logger.handler.LoggerDemo;
import org.demo.logger.handler.console.ConsoleLogger;
import org.demo.logger.handler.database.DatabaseLogger;
import org.demo.logger.handler.file.FileLogger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerDemoImpl implements LoggerDemo {
    private final boolean logToFile;
    private final boolean logToConsole;
    private final boolean logMessage;
    private final boolean logWarning;
    private final boolean logError;
    private final boolean logToDatabase;
    private final Logger logger;

    private final DatabaseLogger databaseLogger;
    private final ConsoleLogger consoleLogger;
    private final FileLogger fileLogger;

    public LoggerDemoImpl(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                          boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, DatabaseLogger databaseLogger, ConsoleLogger consoleLogger, FileLogger fileLogger) {
        this.databaseLogger = databaseLogger;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
        logger = Logger.getLogger("Log Application");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
    }

    public void logMessage(String messageText, boolean message, boolean warning, boolean error) throws NotFoundHandlerException, ConfigurationException, SQLException, IOException {
        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new NotFoundHandlerException("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new ConfigurationException("Error or Warning or Message must be specified");
        }

        if(logToFile) {
            logger.addHandler(fileLogger.getFileHandler());
            logger.log(Level.INFO, messageText);
        }

        if(logToConsole) {
            logger.addHandler(consoleLogger.getConsoleHandler());
            logger.log(Level.INFO, messageText);
        }

        if(logToDatabase) {
            var levelValue = 0;
            if (message && logMessage) {
                levelValue = 1;
            }

            if (error && logError) {
                levelValue = 2;
            }

            if (warning && logWarning) {
                levelValue = 3;
            }
            databaseLogger.runUpdate("insert into Log_Values VALUES('" + messageText + "', " + levelValue + ")");
            logger.log(Level.INFO, messageText);
        }
    }
}
