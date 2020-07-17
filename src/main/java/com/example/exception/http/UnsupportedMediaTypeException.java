package com.example.exception.http;

import org.springframework.http.HttpStatus;

/**
 * Unsupported Media Type 415
 */
public class UnsupportedMediaTypeException extends HttpException {
    private static final long serialVersionUID = 1L;
    public static final HttpStatus HTTP_STATUS = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

    /**
     * Constructs an <code>UnsupportedMediaTypeException</code> with no detail message.
     */
    public UnsupportedMediaTypeException() {
        super(HTTP_STATUS);
    }

    /**
     * Constructs an <code>UnsupportedMediaTypeException</code> with the specified detail message.
     * @param message detail message
     */
    public UnsupportedMediaTypeException(String message) {
        super(HTTP_STATUS, message);
    }

    /**
     * Constructs an <code>UnsupportedMediaTypeException</code> with the specified detail message and cause.
     * @param message detail message
     * @param cause   the cause
     */
    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    /**
     * Constructs an <code>UnsupportedMediaTypeException</code> with the cause.
     * @param cause the cause
     */
    public UnsupportedMediaTypeException(Throwable cause) {
        super(HTTP_STATUS, cause);
    }

}
