package org.demo.logger.exception;

/**
 * Handler Exception
 * @author emilio.watemberg
 */
public class NotFoundHandlerException extends Exception {

	public NotFoundHandlerException(String message) {
        super(message);
    }
}