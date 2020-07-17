package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Unauthorized 401
 */
public class UnauthorizedException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    /**
     * Constructs an <code>UnauthorizedException</code> with no detail message.
     */
    public UnauthorizedException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs an <code>UnauthorizedException</code> with the specified detail message.
     * @param message detail message
     */
    public UnauthorizedException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs an <code>UnauthorizedException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public UnauthorizedException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs an <code>UnauthorizedException</code> with the cause.
     * @param cause the cause
     */
    public UnauthorizedException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
