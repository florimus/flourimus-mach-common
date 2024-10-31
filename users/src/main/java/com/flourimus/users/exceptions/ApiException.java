package com.flourimus.users.exceptions;

public class ApiException extends RuntimeException implements ExceptionFamily {

    private String message = "Service not available. Please try again later.";
    private String errorCode = "SERVICE_NOT_AVAILABLE";
    private int status = 503;

    public ApiException(final String message, final String errorCode, final int status) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
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
