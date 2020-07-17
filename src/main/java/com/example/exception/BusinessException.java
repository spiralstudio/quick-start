package com.example.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a <code>BusinessException</code> with no detail message.
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructs a <code>BusinessException</code> with the specified detail message.
     * @param message detail message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>BusinessException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a <code>BusinessException</code> with the cause.
     * @param cause the cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

}
