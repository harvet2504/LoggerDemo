package org.demo.logger.handler.console.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.ConsoleHandler;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleLoggerImplTest {

    private ConsoleLoggerImpl consoleLogger;

    @BeforeEach
    void setUp() {
        consoleLogger = new ConsoleLoggerImpl();
    }

    @Test
    void getConsoleHandler() {
        ConsoleHandler handler = consoleLogger.getConsoleHandler();
        assertNotNull(handler);
    }
}