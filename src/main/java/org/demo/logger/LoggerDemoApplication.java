package org.demo.logger;

import org.demo.logger.handler.LoggerDemo;
import org.demo.logger.handler.console.impl.ConsoleLoggerImpl;
import org.demo.logger.handler.database.impl.DatabaseLoggerImpl;
import org.demo.logger.handler.file.impl.FileLoggerImpl;
import org.demo.logger.handler.impl.LoggerDemoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerDemoApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LoggerDemoApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LoggerDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LoggerDemo loggerDemo = new LoggerDemoImpl(true,false,false,true,false,false, new DatabaseLoggerImpl(), new ConsoleLoggerImpl(), new FileLoggerImpl());
        loggerDemo.logMessage("Log message for printing in file", true,false, false);
        loggerDemo = new LoggerDemoImpl(false,true,false,true,false,false, new DatabaseLoggerImpl(), new ConsoleLoggerImpl(), new FileLoggerImpl());
        loggerDemo.logMessage("Log message for printing in console", true,false, false);
        loggerDemo = new LoggerDemoImpl(false,false,true,true,false,false, new DatabaseLoggerImpl(), new ConsoleLoggerImpl(), new FileLoggerImpl());
        loggerDemo.logMessage("Log message in database", true,false, false);
    }
}
