package org.demo.logger.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultConfigurationTest {

    private DefaultConfiguration configuration;

    @Test
    void getPropertyFilePath() {
        String property = configuration.getProperty("logger.logFilePath");
        assertEquals("./data/logFile.txt", property);
    }

    @Test
    void getPropertyUserName() {
        String property = configuration.getProperty("logger.bd.userName");
        assertEquals("admin", property);
    }

    @Test
    void getPropertyPassword() {
        String property = configuration.getProperty("logger.bd.password");
        assertEquals("admin", property);
    }

    @Test
    void getPropertyDriver() {
        String property = configuration.getProperty("logger.bd.jdbcDriver");
        assertEquals("org.h2.Driver", property);
    }

    @Test
    void getPropertyDbUrl() {
        String property = configuration.getProperty("logger.bd.dbUrl");
        assertEquals("jdbc:h2:./data/loggerDB", property);
    }

    @BeforeEach
    void setUp() {
        configuration = new DefaultConfiguration();
    }
}