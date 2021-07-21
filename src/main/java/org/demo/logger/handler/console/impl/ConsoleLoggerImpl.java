package org.demo.logger.handler.console.impl;

import org.demo.logger.handler.console.ConsoleLogger;

import java.util.logging.ConsoleHandler;

public class ConsoleLoggerImpl implements ConsoleLogger {
    public ConsoleHandler getConsoleHandler() {
        return new ConsoleHandler();
    }
}
