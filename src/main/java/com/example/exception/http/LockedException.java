package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Locked 423
 */
public class LockedException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.LOCKED;

    /**
     * Constructs a <code>LockedException</code> with no detail message.
     */
    public LockedException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs a <code>LockedException</code> with the specified detail message.
     * @param message detail message
     */
    public LockedException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs a <code>LockedException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public LockedException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs a <code>LockedException</code> with the cause.
     * @param cause the cause
     */
    public LockedException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
