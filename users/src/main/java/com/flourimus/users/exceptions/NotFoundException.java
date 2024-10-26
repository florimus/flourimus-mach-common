package com.flourimus.users.exceptions;


public class NotFoundException extends RuntimeException implements ExceptionFamily {

    private final String message;
    private final String errorCode;
    private int status = 404;

    public NotFoundException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * {@inheritDoc}
     *
     * @return the message that was passed to the constructor
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the error code that was passed to the constructor
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the HTTP status code associated with this exception.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
        return status;
    }

}
