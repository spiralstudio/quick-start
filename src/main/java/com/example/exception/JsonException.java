package com.example.exception;

public class JsonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a <code>JsonException</code> with no detail message.
     */
    public JsonException() {
        super();
    }

    /**
     * Constructs a <code>JsonException</code> with the specified detail message.
     * @param message detail message
     */
    public JsonException(String message) {
        super(message);
    }

    /**
     * Constructs a <code>JsonException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a <code>JsonException</code> with the cause.
     * @param cause the cause
     */
    public JsonException(Throwable cause) {
        super(cause);
    }

}