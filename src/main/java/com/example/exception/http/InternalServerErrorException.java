package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Internal Server Error 500
 */
public class InternalServerErrorException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * Constructs an <code>InternalServerErrorException</code> with no detail message.
     */
    public InternalServerErrorException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs an <code>InternalServerErrorException</code> with the specified detail message.
     * @param message detail message
     */
    public InternalServerErrorException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs an <code>InternalServerErrorException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public InternalServerErrorException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs an <code>InternalServerErrorException</code> with the cause.
     * @param cause the cause
     */
    public InternalServerErrorException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
