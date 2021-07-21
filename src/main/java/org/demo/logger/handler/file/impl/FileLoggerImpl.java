package org.demo.logger.handler.file.impl;

import org.demo.logger.config.DefaultConfiguration;
import org.demo.logger.handler.file.FileLogger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

public class FileLoggerImpl implements FileLogger {

    public FileHandler getFileHandler() throws IOException {
        DefaultConfiguration configuration = new DefaultConfiguration();
        String path = configuration.getProperty("logger.logFilePath");
        var logFile = new File(path);
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        return new FileHandler(path);
    }
}
