package org.demo.logger.handler.file;

import java.io.IOException;
import java.util.logging.FileHandler;

public interface FileLogger {
    FileHandler getFileHandler() throws IOException;
}
