package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Method Not Allowed 405
 */
public class MethodNotAllowedException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.METHOD_NOT_ALLOWED;

    /**
     * Constructs a <code>MethodNotAllowedException</code> with no detail message.
     */
    public MethodNotAllowedException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs a <code>MethodNotAllowedException</code> with the specified detail message.
     * @param message detail message
     */
    public MethodNotAllowedException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a <code>MethodNotAllowedException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public MethodNotAllowedException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs a <code>MethodNotAllowedException</code> with the cause.
     * @param cause the cause
     */
    public MethodNotAllowedException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
