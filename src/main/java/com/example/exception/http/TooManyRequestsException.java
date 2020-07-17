package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Too Many Requests 429
 */
public class TooManyRequestsException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.TOO_MANY_REQUESTS;

    /**
     * Constructs a <code>TooManyRequestsException</code> with no detail message.
     */
    public TooManyRequestsException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs a <code>TooManyRequestsException</code> with the specified detail message.
     * @param message detail message
     */
    public TooManyRequestsException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a <code>TooManyRequestsException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public TooManyRequestsException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs a <code>TooManyRequestsException</code> with the cause.
     * @param cause the cause
     */
    public TooManyRequestsException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
