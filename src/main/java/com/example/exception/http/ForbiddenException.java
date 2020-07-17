package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Forbidden 403
 */
public class ForbiddenException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

    /**
     * Constructs a <code>ForbiddenException</code> with no detail message.
     */
    public ForbiddenException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs a <code>ForbiddenException</code> with the specified detail message.
     * @param message detail message
     */
    public ForbiddenException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a <code>ForbiddenException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public ForbiddenException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs a <code>ForbiddenException</code> with the cause.
     * @param cause the cause
     */
    public ForbiddenException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
