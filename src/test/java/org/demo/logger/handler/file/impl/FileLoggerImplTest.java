package org.demo.logger.handler.file.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerImplTest {

    private FileLoggerImpl fileLogger;

    @BeforeEach
    void setUp() {
        fileLogger = new FileLoggerImpl();
    }

    @Test
    void getFileHandler() throws IOException {
        fileLogger.getFileHandler();
        assert true;
    }
}